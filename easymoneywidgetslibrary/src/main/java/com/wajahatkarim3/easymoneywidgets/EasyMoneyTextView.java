package com.wajahatkarim3.easymoneywidgets;

import com.wajahatkarim3.easymoneywidgets.util.AttrUtil;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import ohos.agp.components.AttrSet;
import ohos.agp.components.Text;
import ohos.app.Context;

/**
 * The TextView widget for support of money requirements like currency, number formatting, comma formatting etc.
 * Add com.wajahatkarim3.easymoneywidgets.EasyMoneyTextView into your XML layouts and you are done!
 * For more information, check http://github.com/wajahatkarim3/EasyMoney-Widgets
 *
 * @author Wajahat Karim (http://wajahatkarim.com)
 * @version 1.0.0 01/20/2017
 */
public class EasyMoneyTextView extends Text {

    private String currencySymbolTextView;
    private boolean showCurrencyTextView;
    private boolean showCommasTextView;

    /**
     * Constructor.
     *
     * @param context Context.
     */
    public EasyMoneyTextView(Context context) {
        super(context);
        initView(context, null);
    }

    /**
     * Constructor.
     *
     * @param context Context.
     * @param attrs AttributeSet.
     */
    public EasyMoneyTextView(Context context, AttrSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttrSet attrs) {
        // Setting Default Parameters
        currencySymbolTextView = Currency.getInstance(Locale.getDefault()).getSymbol();
        showCurrencyTextView = true;
        showCommasTextView = true;

        // Check for the attributes
        if (attrs != null) {
            // Attribute initialization
            String currency = AttrUtil.getStringValue(attrs,
                    AttrUtil.getString(context, ResourceTable.String_currency_symbol_text_view), null);
            if (currency == null) {
                currency = Currency.getInstance(Locale.getDefault()).getSymbol();
            }
            setCurrency(currency);

            showCurrencyTextView = AttrUtil.getBooleanValue(attrs,
                    AttrUtil.getString(context, ResourceTable.String_show_currency_text_view), true);
            showCommasTextView = AttrUtil.getBooleanValue(attrs,
                    AttrUtil.getString(context, ResourceTable.String_show_commas_text_view), true);
        }
    }

    private String getDecoratedStringFromNumber(long number) {
        String numberPattern = "#,###,###,###";
        String decoStr;

        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.getDefault());
        if (showCommasTextView && !showCurrencyTextView) {
            formatter.applyPattern(numberPattern);
        } else if (showCommasTextView) {
            formatter.applyPattern(currencySymbolTextView + " " + numberPattern);
        } else if (showCurrencyTextView) {
            formatter.applyPattern(currencySymbolTextView + " ");
        } else {
            decoStr = number + "";
            return decoStr;
        }
        decoStr = formatter.format(number);
        return decoStr;
    }

    private void setValue(String valueTextView) {
        try {
            long longval;

            valueTextView = getValueString();
            longval = (Long.parseLong(valueTextView));
            String formattedString = getDecoratedStringFromNumber(longval);

            // setting text after format to TextView
            setText(formattedString);

        } catch (NumberFormatException nfe) {
            setText(valueTextView);
            String valTextView = getValueString();

            if (valTextView.equals("")) {
                long val = 0;
                setText(getDecoratedStringFromNumber(val));
            } else {
                // Some decimal number
                if (valTextView.contains(".")) {
                    if (valTextView.indexOf(".") == valTextView.length() - 1) {
                        // decimal has been currently put
                        String front = getDecoratedStringFromNumber(
                                Long.parseLong(valTextView.substring(0, valTextView.length() - 1)));
                        setText(front + ".");
                    } else {
                        String[] nums = getValueString().split("\\.");
                        String front = getDecoratedStringFromNumber(Long.parseLong(nums[0]));
                        setText(front + "." + nums[1]);
                    }
                }
            }
        }
    }

    /**
     * Get the value of the text without any commas and currency.
     * For example, if the edit text value is $ 1,34,000.60 then this method will return 134000.60
     *
     * @return A string of the raw value in the text field
     */
    public String getValueString() {

        String string = getText();

        if (string.contains(",")) {
            string = string.replace(",", "");
        }
        if (string.contains(" ")) {
            string = string.substring(string.indexOf(" ") + 1);
        }
        return string;
    }

    /**
     * Get the value of the text with formatted commas and currency.
     * For example, if the edit text value is $ 1,34,000.60 then this method will return exactly $ 1,34,000.60
     *
     * @return A string of the text value in the text field
     */
    public String getFormattedString() {
        setValue(getText());
        return getText();
    }

    /**
     * Set the currency symbol for the edit text.
     *
     * @param newSymbol the new symbol of currency in string
     */
    public void setCurrency(String newSymbol) {
        currencySymbolTextView = newSymbol;
        setValue(getText());
    }

    /**
     * Set the currency symbol for the edit text.
     *
     * @param locale the locale of new symbol. (Defaul is Locale.Default)
     */
    public void setCurrency(Locale locale) {
        setCurrency(Currency.getInstance(locale).getSymbol());
    }

    /**
     * Set the currency symbol for the edit text. (Default is US Dollar $).
     *
     * @param currency the currency object of new symbol. (Defaul is Locale.US)
     */
    public void setCurrency(Currency currency) {
        setCurrency(currency.getSymbol());
    }

    public void setShowCurrency(boolean value) {
        showCurrencyTextView = value;
        setValue(getText());
    }

    /**
     * Whether currency is shown in the text or not. (Default is true)
     *
     * @return true if the currency is shown otherwise false.
     */
    public boolean isShowCurrency() {
        return showCurrencyTextView;
    }

    /**
     * Shows the currency in the text. (Default is shown).
     */
    public void showCurrencySymbol() {
        setShowCurrency(true);
    }

    /**
     * Hides the currency in the text. (Default is shown).
     */
    public void hideCurrencySymbol() {
        setShowCurrency(false);
    }

    /**
     * Shows the commas in the text. (Default is shown).
     */
    public void showCommas() {
        showCommasTextView = true;
        setValue(getText());
    }

    /**
     * Hides the commas in the text. (Default is shown).
     */
    public void hideCommas() {
        showCommasTextView = false;
        setValue(getText());
    }
}
