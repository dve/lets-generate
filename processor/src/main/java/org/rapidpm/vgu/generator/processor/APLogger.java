/**
 * Copyright © 2019 Daniel Nordhoff-Vergien (dve@vergien.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.rapidpm.vgu.generator.processor;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class APLogger {
  private final ProcessingEnvironment processingEnvironment;

  public APLogger(ProcessingEnvironment processingEnvironment) {
    super();
    this.processingEnvironment = processingEnvironment;
  }

  public void error(String msg, Element e, Throwable t) {
    String stackTrace = t == null ? null : ExceptionUtils.getStackTrace(t);
    String messageWithStackTrace = StringUtils.join(msg, "\n", stackTrace);
    if (e != null)
      processingEnvironment.getMessager().printMessage(Diagnostic.Kind.ERROR, messageWithStackTrace,
          e);
    else
      processingEnvironment.getMessager().printMessage(Diagnostic.Kind.ERROR,
          messageWithStackTrace);
  }

  public void error(String msg, Throwable t) {
    error(msg, null, t);
  }

  public void warn(String msg, Element e) {
    processingEnvironment.getMessager().printMessage(Diagnostic.Kind.WARNING, msg, e);
  }

  public void info(String msg, Element e) {
    processingEnvironment.getMessager().printMessage(Diagnostic.Kind.NOTE, msg, e);
  }

  public void info(String msg) {
    processingEnvironment.getMessager().printMessage(Diagnostic.Kind.NOTE, msg);
  }
}
