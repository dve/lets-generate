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
package org.rapidpm.vgu.generator.processor;

import static com.google.testing.compile.CompilationSubject.assertThat;
import static com.google.testing.compile.Compiler.javac;
import org.junit.jupiter.api.Test;
import com.google.testing.compile.Compilation;
import com.google.testing.compile.JavaFileObjects;
import net.vergien.beanautoutils.processor.BeanAutoUtilsProcessor;

class TestDataBeanAnnotationProccessor {

  @Test
  void test() {
    // compile
    Compilation compilation = javac()
        .withProcessors(new DataBeanAnnotationProccessor(), new BeanAutoUtilsProcessor(),
            new VaadinDataBeanAnnotationProcessor())
        .compile(JavaFileObjects.forResource("SimpleBean.java"),
            JavaFileObjects.forResource("VaadinDataBeanConfig.java"));
  //  assertThat(compilation).succeeded();
  }

}
