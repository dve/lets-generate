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

import org.rapidpm.vgu.generator.annotation.VaadinDataBeans;
import de.generator.beans.Address;
import de.generator.beans.Contract;
import de.generator.beans.Types;

@VaadinDataBeans({Address.class, Contract.class, Types.class})
public interface VaadinConfig {

}
