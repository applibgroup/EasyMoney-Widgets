package com.wajahatkarim3.easymoneywidgets.demo;

import com.wajahatkarim3.easymoneywidgets.EasyMoneyEditText;
import com.wajahatkarim3.easymoneywidgets.EasyMoneyTextView;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.agp.components.Attr;
import ohos.agp.components.AttrSet;
import ohos.app.Context;
import org.junit.Before;
import org.junit.Test;

import java.util.Currency;
import java.util.Locale;
import java.util.Optional;

import static org.junit.Assert.*;

public class ExampleOhosTest {
    private Context context;
    private AttrSet attrSet;
    private EasyMoneyEditText easyMoneyEditText;
    private EasyMoneyTextView easyMoneyTextView;
    private static final String DEFAULT_CURRENCY = Currency.getInstance(Locale.getDefault()).getSymbol();
    private static final String EURO = "€";
    private static final String POUND = "£";
    private static final String INPUT = "$ 134,000.60";
    private static final String OUTPUT_NONE = "134000.60";
    private static final String OUTPUT_COMMAS_ONLY = "134,000.60";
    private static final String OUTPUT_CURRENCY_ONLY = " 134000.60";
    private static final String OUTPUT_CURRENCY_COMMAS = " 134,000.60";

    @Before
    public void setUp() {
        context = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        attrSet = new AttrSet() {
            @Override
            public Optional<String> getStyle() {
                return Optional.empty();
            }

            @Override
            public int getLength() {
                return 0;
            }

            @Override
            public Optional<Attr> getAttr(int i) {
                return Optional.empty();
            }

            @Override
            public Optional<Attr> getAttr(String s) {
                return Optional.empty();
            }
        };
    }

    // Tests for EasyMoneyEditText

    @Test
    public void testEditTextSetCurrencyString() {
        easyMoneyEditText = new EasyMoneyEditText(context);
        easyMoneyEditText.setText(INPUT);
        easyMoneyEditText.setCurrency("€");
        assertEquals(EURO + OUTPUT_CURRENCY_COMMAS, easyMoneyEditText.getFormattedString());
    }

    @Test
    public void testEditTextSetCurrencyLocale() {
        easyMoneyEditText = new EasyMoneyEditText(context);
        easyMoneyEditText.setText(INPUT);
        easyMoneyEditText.setCurrency(Locale.UK);
        assertEquals(POUND + OUTPUT_CURRENCY_COMMAS, easyMoneyEditText.getFormattedString());
    }

    @Test
    public void testEditTextSetCurrencyCurrency() {
        easyMoneyEditText = new EasyMoneyEditText(context);
        easyMoneyEditText.setText(INPUT);
        easyMoneyEditText.setCurrency(Currency.getInstance(Locale.ITALY));
        assertEquals(EURO + OUTPUT_CURRENCY_COMMAS, easyMoneyEditText.getFormattedString());
    }

    @Test
    public void testEditTextIsShowCurrencyDefault() {
        easyMoneyEditText = new EasyMoneyEditText(context);
        easyMoneyEditText.setText(INPUT);
        assertTrue(easyMoneyEditText.isShowCurrency());
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_COMMAS, easyMoneyEditText.getFormattedString());
    }

    @Test
    public void testEditTextIsShowCurrencyShow() {
        easyMoneyEditText = new EasyMoneyEditText(context);
        easyMoneyEditText.setText(INPUT);
        easyMoneyEditText.showCurrencySymbol();
        assertTrue(easyMoneyEditText.isShowCurrency());
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_COMMAS, easyMoneyEditText.getFormattedString());
    }

    @Test
    public void testEditTextIsShowCurrencyHide() {
        easyMoneyEditText = new EasyMoneyEditText(context);
        easyMoneyEditText.setText(INPUT);
        easyMoneyEditText.hideCurrencySymbol();
        assertFalse(easyMoneyEditText.isShowCurrency());
        assertEquals(OUTPUT_COMMAS_ONLY, easyMoneyEditText.getFormattedString());
    }

    @Test
    public void testEditTextCommasDefault() {
        easyMoneyEditText = new EasyMoneyEditText(context);
        easyMoneyEditText.setText(INPUT);
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_COMMAS, easyMoneyEditText.getFormattedString());
    }

    @Test
    public void testEditTextCommasShow() {
        easyMoneyEditText = new EasyMoneyEditText(context);
        easyMoneyEditText.setText(INPUT);
        easyMoneyEditText.showCommas();
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_COMMAS, easyMoneyEditText.getFormattedString());
    }

    @Test
    public void testEditTextCommasHide() {
        easyMoneyEditText = new EasyMoneyEditText(context);
        easyMoneyEditText.setText(INPUT);
        easyMoneyEditText.hideCommas();
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_ONLY, easyMoneyEditText.getFormattedString());
    }

    @Test
    public void testEditTextGetValueString() {
        easyMoneyEditText = new EasyMoneyEditText(context);
        easyMoneyEditText.setText(INPUT);
        assertEquals(OUTPUT_NONE, easyMoneyEditText.getValueString());
    }

    @Test
    public void testEditTextGetFormattedString() {
        easyMoneyEditText = new EasyMoneyEditText(context);
        easyMoneyEditText.setText(INPUT);
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_COMMAS, easyMoneyEditText.getFormattedString());
    }

    @Test
    public void testEditTextWithAttrSetSetCurrencyString() {
        easyMoneyEditText = new EasyMoneyEditText(context, attrSet);
        easyMoneyEditText.setText(INPUT);
        easyMoneyEditText.setCurrency("€");
        assertEquals(EURO + OUTPUT_CURRENCY_COMMAS, easyMoneyEditText.getFormattedString());
    }

    @Test
    public void testEditTextWithAttrSetSetCurrencyLocale() {
        easyMoneyEditText = new EasyMoneyEditText(context, attrSet);
        easyMoneyEditText.setText(INPUT);
        easyMoneyEditText.setCurrency(Locale.UK);
        assertEquals(POUND + OUTPUT_CURRENCY_COMMAS, easyMoneyEditText.getFormattedString());
    }

    @Test
    public void testEditTextWithAttrSetSetCurrencyCurrency() {
        easyMoneyEditText = new EasyMoneyEditText(context, attrSet);
        easyMoneyEditText.setText(INPUT);
        easyMoneyEditText.setCurrency(Currency.getInstance(Locale.ITALY));
        assertEquals(EURO + OUTPUT_CURRENCY_COMMAS, easyMoneyEditText.getFormattedString());
    }

    @Test
    public void testEditTextWithAttrSetIsShowCurrencyDefault() {
        easyMoneyEditText = new EasyMoneyEditText(context, attrSet);
        easyMoneyEditText.setText(INPUT);
        assertTrue(easyMoneyEditText.isShowCurrency());
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_COMMAS, easyMoneyEditText.getFormattedString());
    }

    @Test
    public void testEditTextWithAttrSetIsShowCurrencyShow() {
        easyMoneyEditText = new EasyMoneyEditText(context, attrSet);
        easyMoneyEditText.setText(INPUT);
        easyMoneyEditText.showCurrencySymbol();
        assertTrue(easyMoneyEditText.isShowCurrency());
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_COMMAS, easyMoneyEditText.getFormattedString());
    }

    @Test
    public void testEditTextWithAttrSetIsShowCurrencyHide() {
        easyMoneyEditText = new EasyMoneyEditText(context, attrSet);
        easyMoneyEditText.setText(INPUT);
        easyMoneyEditText.hideCurrencySymbol();
        assertFalse(easyMoneyEditText.isShowCurrency());
        assertEquals(OUTPUT_COMMAS_ONLY, easyMoneyEditText.getFormattedString());
    }

    @Test
    public void testEditTextWithAttrSetCommasDefault() {
        easyMoneyEditText = new EasyMoneyEditText(context, attrSet);
        easyMoneyEditText.setText(INPUT);
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_COMMAS, easyMoneyEditText.getFormattedString());
    }

    @Test
    public void testEditTextWithAttrSetCommasShow() {
        easyMoneyEditText = new EasyMoneyEditText(context, attrSet);
        easyMoneyEditText.setText(INPUT);
        easyMoneyEditText.showCommas();
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_COMMAS, easyMoneyEditText.getFormattedString());
    }

    @Test
    public void testEditTextWithAttrSetCommasHide() {
        easyMoneyEditText = new EasyMoneyEditText(context, attrSet);
        easyMoneyEditText.setText(INPUT);
        easyMoneyEditText.hideCommas();
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_ONLY, easyMoneyEditText.getFormattedString());
    }

    @Test
    public void testEditTextWithAttrSetGetValueString() {
        easyMoneyEditText = new EasyMoneyEditText(context, attrSet);
        easyMoneyEditText.setText(INPUT);
        assertEquals(OUTPUT_NONE, easyMoneyEditText.getValueString());
    }

    @Test
    public void testEditTextWithAttrSetGetFormattedString() {
        easyMoneyEditText = new EasyMoneyEditText(context, attrSet);
        easyMoneyEditText.setText(INPUT);
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_COMMAS, easyMoneyEditText.getFormattedString());
    }

    // Tests for EasyMoneyTextView

    @Test
    public void testTextViewSetCurrencyString() {
        easyMoneyTextView = new EasyMoneyTextView(context);
        easyMoneyTextView.setText(INPUT);
        easyMoneyTextView.setCurrency("€");
        assertEquals(EURO + OUTPUT_CURRENCY_COMMAS, easyMoneyTextView.getFormattedString());
    }

    @Test
    public void testTextViewSetCurrencyLocale() {
        easyMoneyTextView = new EasyMoneyTextView(context);
        easyMoneyTextView.setText(INPUT);
        easyMoneyTextView.setCurrency(Locale.UK);
        assertEquals(POUND + OUTPUT_CURRENCY_COMMAS, easyMoneyTextView.getFormattedString());
    }

    @Test
    public void testTextViewSetCurrencyCurrency() {
        easyMoneyTextView = new EasyMoneyTextView(context);
        easyMoneyTextView.setText(INPUT);
        easyMoneyTextView.setCurrency(Currency.getInstance(Locale.ITALY));
        assertEquals(EURO + OUTPUT_CURRENCY_COMMAS, easyMoneyTextView.getFormattedString());
    }

    @Test
    public void testTextViewIsShowCurrencyDefault() {
        easyMoneyTextView = new EasyMoneyTextView(context);
        easyMoneyTextView.setText(INPUT);
        assertTrue(easyMoneyTextView.isShowCurrency());
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_COMMAS, easyMoneyTextView.getFormattedString());
    }

    @Test
    public void testTextViewIsShowCurrencyShow() {
        easyMoneyTextView = new EasyMoneyTextView(context);
        easyMoneyTextView.setText(INPUT);
        easyMoneyTextView.showCurrencySymbol();
        assertTrue(easyMoneyTextView.isShowCurrency());
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_COMMAS, easyMoneyTextView.getFormattedString());
    }

    @Test
    public void testTextViewIsShowCurrencyHide() {
        easyMoneyTextView = new EasyMoneyTextView(context);
        easyMoneyTextView.setText(INPUT);
        easyMoneyTextView.hideCurrencySymbol();
        assertFalse(easyMoneyTextView.isShowCurrency());
        assertEquals(OUTPUT_COMMAS_ONLY, easyMoneyTextView.getFormattedString());
    }

    @Test
    public void testTextViewCommasDefault() {
        easyMoneyTextView = new EasyMoneyTextView(context);
        easyMoneyTextView.setText(INPUT);
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_COMMAS, easyMoneyTextView.getFormattedString());
    }

    @Test
    public void testTextViewCommasShow() {
        easyMoneyTextView = new EasyMoneyTextView(context);
        easyMoneyTextView.setText(INPUT);
        easyMoneyTextView.showCommas();
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_COMMAS, easyMoneyTextView.getFormattedString());
    }

    @Test
    public void testTextViewCommasHide() {
        easyMoneyTextView = new EasyMoneyTextView(context);
        easyMoneyTextView.setText(INPUT);
        easyMoneyTextView.hideCommas();
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_ONLY, easyMoneyTextView.getFormattedString());
    }

    @Test
    public void testTextViewGetValueString() {
        easyMoneyTextView = new EasyMoneyTextView(context);
        easyMoneyTextView.setText(INPUT);
        assertEquals(OUTPUT_NONE, easyMoneyTextView.getValueString());
    }

    @Test
    public void testTextViewGetFormattedString() {
        easyMoneyTextView = new EasyMoneyTextView(context);
        easyMoneyTextView.setText(INPUT);
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_COMMAS, easyMoneyTextView.getFormattedString());
    }

    @Test
    public void testTextViewWithAttrSetSetCurrencyString() {
        easyMoneyTextView = new EasyMoneyTextView(context, attrSet);
        easyMoneyTextView.setText(INPUT);
        easyMoneyTextView.setCurrency("€");
        assertEquals(EURO + OUTPUT_CURRENCY_COMMAS, easyMoneyTextView.getFormattedString());
    }

    @Test
    public void testTextViewWithAttrSetSetCurrencyLocale() {
        easyMoneyTextView = new EasyMoneyTextView(context, attrSet);
        easyMoneyTextView.setText(INPUT);
        easyMoneyTextView.setCurrency(Locale.UK);
        assertEquals(POUND + OUTPUT_CURRENCY_COMMAS, easyMoneyTextView.getFormattedString());
    }

    @Test
    public void testTextViewWithAttrSetSetCurrencyCurrency() {
        easyMoneyTextView = new EasyMoneyTextView(context, attrSet);
        easyMoneyTextView.setText(INPUT);
        easyMoneyTextView.setCurrency(Currency.getInstance(Locale.ITALY));
        assertEquals(EURO + OUTPUT_CURRENCY_COMMAS, easyMoneyTextView.getFormattedString());
    }

    @Test
    public void testTextViewWithAttrSetIsShowCurrencyDefault() {
        easyMoneyTextView = new EasyMoneyTextView(context, attrSet);
        easyMoneyTextView.setText(INPUT);
        assertTrue(easyMoneyTextView.isShowCurrency());
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_COMMAS, easyMoneyTextView.getFormattedString());
    }

    @Test
    public void testTextViewWithAttrSetIsShowCurrencyShow() {
        easyMoneyTextView = new EasyMoneyTextView(context, attrSet);
        easyMoneyTextView.setText(INPUT);
        easyMoneyTextView.showCurrencySymbol();
        assertTrue(easyMoneyTextView.isShowCurrency());
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_COMMAS, easyMoneyTextView.getFormattedString());
    }

    @Test
    public void testTextViewWithAttrSetIsShowCurrencyHide() {
        easyMoneyTextView = new EasyMoneyTextView(context, attrSet);
        easyMoneyTextView.setText(INPUT);
        easyMoneyTextView.hideCurrencySymbol();
        assertFalse(easyMoneyTextView.isShowCurrency());
        assertEquals(OUTPUT_COMMAS_ONLY, easyMoneyTextView.getFormattedString());
    }

    @Test
    public void testTextViewWithAttrSetCommasDefault() {
        easyMoneyTextView = new EasyMoneyTextView(context, attrSet);
        easyMoneyTextView.setText(INPUT);
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_COMMAS, easyMoneyTextView.getFormattedString());
    }

    @Test
    public void testTextViewWithAttrSetCommasShow() {
        easyMoneyTextView = new EasyMoneyTextView(context, attrSet);
        easyMoneyTextView.setText(INPUT);
        easyMoneyTextView.showCommas();
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_COMMAS, easyMoneyTextView.getFormattedString());
    }

    @Test
    public void testTextViewWithAttrSetCommasHide() {
        easyMoneyTextView = new EasyMoneyTextView(context, attrSet);
        easyMoneyTextView.setText(INPUT);
        easyMoneyTextView.hideCommas();
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_ONLY, easyMoneyTextView.getFormattedString());
    }

    @Test
    public void testTextViewWithAttrSetGetValueString() {
        easyMoneyTextView = new EasyMoneyTextView(context, attrSet);
        easyMoneyTextView.setText(INPUT);
        assertEquals(OUTPUT_NONE, easyMoneyTextView.getValueString());
    }

    @Test
    public void testTextViewWithAttrSetGetFormattedString() {
        easyMoneyTextView = new EasyMoneyTextView(context, attrSet);
        easyMoneyTextView.setText(INPUT);
        assertEquals(DEFAULT_CURRENCY + OUTPUT_CURRENCY_COMMAS, easyMoneyTextView.getFormattedString());
    }
}