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

import org.rapidpm.vgu.generator.annotation.CustomFilter;
import org.rapidpm.vgu.generator.annotation.DataBean;
import org.rapidpm.vgu.generator.annotation.DisplayReadOnly;
import org.rapidpm.vgu.generator.annotation.FilterProperty;
import org.rapidpm.vgu.generator.annotation.SortProperty;
import org.rapidpm.vgu.generator.annotation.VaadinDataBeans;
import net.java.dev.hickory.prism.GeneratePrism;
import net.java.dev.hickory.prism.GeneratePrisms;

@GeneratePrisms({@GeneratePrism(value = DataBean.class, publicAccess = true),
    @GeneratePrism(value = FilterProperty.class, publicAccess = true),
    @GeneratePrism(value = SortProperty.class, publicAccess = true),
    @GeneratePrism(value = CustomFilter.class, publicAccess = true),
    @GeneratePrism(value = VaadinDataBeans.class, publicAccess = true),
    @GeneratePrism(value = DisplayReadOnly.class, publicAccess = true)})
public class PrisimGenerator {

}
