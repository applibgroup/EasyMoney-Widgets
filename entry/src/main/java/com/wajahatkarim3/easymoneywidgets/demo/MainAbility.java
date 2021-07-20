package com.wajahatkarim3.easymoneywidgets.demo;

import com.wajahatkarim3.easymoneywidgets.demo.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

/**
 * Ability to test EasyMoneyEditText and EasyMoneyTextView widgets.
 */
public class MainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());
    }
}
