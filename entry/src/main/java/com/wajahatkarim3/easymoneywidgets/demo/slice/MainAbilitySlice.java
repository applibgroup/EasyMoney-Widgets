package com.wajahatkarim3.easymoneywidgets.demo.slice;

import com.wajahatkarim3.easymoneywidgets.EasyMoneyEditText;
import com.wajahatkarim3.easymoneywidgets.EasyMoneyTextView;
import com.wajahatkarim3.easymoneywidgets.demo.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Checkbox;
import ohos.agp.components.Picker;

/**
 * AbilitySlice to test EasyMoneyEditText and EasyMoneyTextView widgets.
 */
public class MainAbilitySlice extends AbilitySlice {
    EasyMoneyEditText moneyEditText;
    EasyMoneyTextView moneyTextView;
    Picker pickerCurrency;
    String[] currencyArray = new String[]
            {"Chinese Yuan (¥)", "Indian Rupee (₹)", "US Dollar ($)", "Euro (€)", "British Pound (£)"};

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
