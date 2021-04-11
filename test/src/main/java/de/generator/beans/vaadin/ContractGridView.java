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
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import de.generator.beans.Contract;

@Route("contractGrid")
public class ContractGridView extends Composite<VerticalLayout> {
  public ContractGridView() {
    ContractGrid contractGrid = new ContractGrid() {
      @Override
      protected Column<Contract> createAddressColumn() {
        Column<Contract> addColumn = getGrid().addColumn(
            contract -> contract.getAddress() != null ? contract.getAddress().computeCaption()
                : "");
        return addColumn;
      }
    };
    contractGrid.setBaseQueries(new ContractQueries());
    contractGrid.setAddressBaseQueries(new AddressQueries());
    contractGrid.setSizeFull();

    ContractForm details = new ContractForm();
    details.setAddressBaseQueries(new AddressQueries());
    contractGrid.getGrid().addSelectionListener(
        event -> details.setValue(event.getFirstSelectedItem().orElseGet(null)));
    getContent().add(new VerticalLayout(contractGrid, details));
  }
}
