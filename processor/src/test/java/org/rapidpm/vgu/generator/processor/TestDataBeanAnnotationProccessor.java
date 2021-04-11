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
