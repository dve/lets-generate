/**
 * Copyright © 2019 Daniel Nordhoff-Vergien (dve@vergien.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rapidpm.vgu.generator.processor;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.vgu.generator.annotation.DataBean;
import org.rapidpm.vgu.generator.codegenerator.FilterGenerator;
import org.rapidpm.vgu.generator.codegenerator.QueryInterfaceGenerator;
import org.rapidpm.vgu.generator.codegenerator.SortPropertyGenerator;
import org.rapidpm.vgu.generator.model.DataBeanModel;
import com.google.auto.service.AutoService;


@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class DataBeanAnnotationProccessor extends AbstractDataBeanProcessor implements HasLogger {
  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    for (TypeElement annotation : annotations) {
      try {
        logger().info("Process anotation {}", annotation);
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
        for (Element e : annotatedElements) {
          logger().info("Process element {} of type {} with annotaded with {}", e,
              e.getClass().getName(), annotation);

          TypeElement typeElement = (TypeElement) e;

          DataBeanModel dataBeanModel = process(typeElement);
          logger().info("DataBeanModel: {}", dataBeanModel);
          write(typeElement, dataBeanModel);
        }
      } catch (Exception e) {
        logger().severe("Failure proccessing", e);
        error("Failure proccessing dataBean", annotation, e);
      }
    }
    return false;
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    Set<String> supportedAnnotations = new HashSet<>(Arrays.asList(DataBean.class.getName()));
    return supportedAnnotations;
  }

  @Override
  public void write(TypeElement typeElement, DataBeanModel dataBeanModel) {
    try {
      FilterGenerator filterGenerator = new FilterGenerator();
      filterGenerator.writeCode(processingEnv, dataBeanModel);

      SortPropertyGenerator sortPropertyGenerator = new SortPropertyGenerator();
      sortPropertyGenerator.writeCode(processingEnv, dataBeanModel);

      QueryInterfaceGenerator queryInterfaceGenerator = new QueryInterfaceGenerator();
      queryInterfaceGenerator.writeCode(processingEnv, dataBeanModel);
    } catch (IOException e1) {
      logger().severe("Failrue writing code", e1);
      error("Failure writing code", typeElement, e1);
    }
  }
}
