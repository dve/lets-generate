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
package de.generator.beans.vaadin;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.vaadin.flow.i18n.I18NProvider;

public class I18N implements I18NProvider {

  private static final String BUNDLE_NAME = "Translation";
  private static final Logger LOGGER = LoggerFactory.getLogger(I18N.class);

  @Override
  public List<Locale> getProvidedLocales() {
    return Arrays.asList(Locale.GERMAN, Locale.ENGLISH);
  }

  @Override
  public String getTranslation(String key, Locale locale, Object... params) {
    try {
      ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
      String string = bundle.getString(key);
      return new MessageFormat(string, locale).format(params);
    } catch (MissingResourceException e) {
      return defaultString(key, locale);
    }


  }

  private String defaultString(String key, Locale locale) {
    LOGGER.error(
        "The key \"" + key + "\" is not in the resource bundel for the locale \"" + locale + "\".");
    return '!' + key + '!';
  }

}
