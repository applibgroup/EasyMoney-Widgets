/*
 * Copyright (C) 2020-21 Application Library Engineering Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wajahatkarim3.easymoneywidgets.demo.slice;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Checkbox;
import ohos.agp.components.Picker;
import com.wajahatkarim3.easymoneywidgets.EasyMoneyEditText;
import com.wajahatkarim3.easymoneywidgets.EasyMoneyTextView;
import com.wajahatkarim3.easymoneywidgets.demo.ResourceTable;

/**
 * AbilitySlice to test EasyMoneyEditText and EasyMoneyTextView widgets.
 */
public class MainAbilitySlice extends AbilitySlice {
    EasyMoneyEditText moneyEditText;
    EasyMoneyTextView moneyTextView;
    Picker pickerCurrency;
    String[] currencyArray = new String[]
            {"US Dollar ($)", "Chinese Yuan (¥)", "Indian Rupee (₹)", "Euro (€)", "British Pound (£)"};

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        moneyEditText = (EasyMoneyEditText) findComponentById(ResourceTable.Id_moneyEditText);
        moneyTextView = (EasyMoneyTextView) findComponentById(ResourceTable.Id_moneyTextView);
        pickerCurrency = (Picker) findComponentById(ResourceTable.Id_pickerCurrency);

        Checkbox checkCurrency = (Checkbox) findComponentById(ResourceTable.Id_checkCurrency);
        checkCurrency.setCheckedStateChangedListener((absButton, b) -> {
            if (b) {
                moneyEditText.showCurrencySymbol();
                moneyTextView.showCurrencySymbol();
            } else {
                moneyEditText.hideCurrencySymbol();
                moneyTextView.hideCurrencySymbol();
            }
        });

        Checkbox checkCommas = (Checkbox) findComponentById(ResourceTable.Id_checkCommas);
        checkCommas.setCheckedStateChangedListener((absButton, b) -> {
            if (b) {
                moneyEditText.showCommas();
                moneyTextView.showCommas();
            } else {
                moneyEditText.hideCommas();
                moneyTextView.hideCommas();
            }
        });

        pickerCurrency.setMinValue(0);
        pickerCurrency.setMaxValue(4);

        pickerCurrency.setDisplayedData(currencyArray);
        pickerCurrency.setValue(0);

        pickerCurrency.setValueChangedListener((picker, i, i1) -> {
            String itemName = picker.getDisplayedData()[i1];
            String symbol = itemName.substring(itemName.indexOf("(") + 1, itemName.indexOf(")"));
            moneyEditText.setCurrency(symbol);
            moneyTextView.setCurrency(symbol);
        });

    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
