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

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import de.generator.beans.Address;

@Route("form")
public class Form extends Composite<VerticalLayout> {
  public Form() {
    AddressForm addressForm = new AddressForm();
    Address address = new Address();

    address.setFristName("Daniel");
    address.setLastName("Nordhoff-Vergien");
    address.setId(123);
    address.setAge(77);
    address.setPhone("123-33-22");
    addressForm.setValue(address);
    getContent().add(addressForm);
    addressForm.addValueChangeListener(
        e -> Notification.show(e.getOldValue().toString() + "\n" + e.getValue().toString()));
  }

}
