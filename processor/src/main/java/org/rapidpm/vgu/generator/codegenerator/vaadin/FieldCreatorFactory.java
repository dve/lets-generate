/*
 * Copyright © 2021 Daniel Nordhoff-Vergien (dve@vergien.net)
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
package org.rapidpm.vgu.generator.codegenerator.vaadin;

import java.util.Comparator;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.StreamSupport;
import org.rapidpm.vgu.generator.processor.VaadinDataBeanAnnotationProcessor;
import com.squareup.javapoet.TypeName;

public class FieldCreatorFactory {

  private ServiceLoader<FieldCreator> fieldCreatorLoader;

  public FieldCreatorFactory() {
    fieldCreatorLoader = ServiceLoader.load(FieldCreator.class,
        VaadinDataBeanAnnotationProcessor.class.getClassLoader());

    StringBuilder sb = new StringBuilder("Available fieldCreators:\n");
    for (FieldCreator fc : fieldCreatorLoader) {
      sb.append("\t");
      sb.append(fc.getClass().getName());
      sb.append("\n");
    }
    System.out.println(sb.toString());
  }

  public Optional<FieldCreator> getFieldCreator(TypeName typeName, FieldType fieldType) {
    return StreamSupport.stream(fieldCreatorLoader.spliterator(), false)
        .filter(creator -> creator.isResponsibleFor(typeName))
        .sorted(Comparator
            .comparingInt(fieldCreator -> ((FieldCreator) fieldCreator).getPriority(fieldType))
            .reversed())
        .findFirst();
  }
}
