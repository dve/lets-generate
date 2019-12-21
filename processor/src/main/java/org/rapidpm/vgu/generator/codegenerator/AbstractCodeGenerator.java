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
package org.rapidpm.vgu.generator.codegenerator;

import static org.rapidpm.vgu.generator.codegenerator.ClassNameUtils.appendSubPackage;
import java.io.IOException;
import java.io.Writer;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import org.apache.commons.lang3.StringUtils;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.vgu.generator.model.DataBeanModel;
import org.rapidpm.vgu.generator.processor.APLogger;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

public abstract class AbstractCodeGenerator implements CodeGenerator, HasLogger {
  protected APLogger apLogger = null;
  protected ProcessingEnvironment processingEnvironment;

  protected void setProccesingEnviroment(ProcessingEnvironment processingEnvironment) {
    this.processingEnvironment = processingEnvironment;
    this.apLogger = new APLogger(processingEnvironment);
  }

  public void writeClass(Filer filer, DataBeanModel model, TypeSpec typeSpec) throws IOException {
    String packageSuffix = packageSuffix();

    JavaFile sourceFile = JavaFile
        .builder(StringUtils.join(new String[] {model.getPackage(), packageSuffix}, "."), typeSpec)
        .build();
    try (Writer writer =
        filer.createSourceFile(appendSubPackage(model.getFqnNAme() + classSuffix(), packageSuffix))
            .openWriter()) {
      sourceFile.writeTo(writer);
      logger().info("Wrote file: {}.{}", sourceFile.packageName, sourceFile.typeSpec.name);
    }
  }

  protected String packageName(DataBeanModel model) {
    return StringUtils.join(new String[] {model.getPackage(), packageSuffix()}, ".");
  }

  public abstract String packageSuffix();

  public abstract String classSuffix();
}
