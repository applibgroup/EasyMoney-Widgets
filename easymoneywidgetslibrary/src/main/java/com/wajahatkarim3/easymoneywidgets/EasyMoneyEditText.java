package com.wajahatkarim3.easymoneywidgets;

import com.wajahatkarim3.easymoneywidgets.util.AttrUtil;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import ohos.agp.components.AttrSet;
import ohos.agp.components.Text;
import ohos.agp.components.TextField;
import ohos.app.Context;


/**
 * The EditText widget for support of money requirements like currency, number formatting, comma formatting etc.
 * Add com.wajahatkarim3.easymoneywidgets.EasyMoneyEditText into your XML layouts and you are done!
 * For more information, check http://github.com/wajahatkarim3/EasyMoney-Widgets
 *
 * @author Wajahat Karim (http://wajahatkarim.com)
 * @version 1.0.0 01/20/2017
 */
public class EasyMoneyEditText extends TextField {

    private String currencySymbolEditText;
    private boolean showCurrencyEditText;
    private boolean showCommasEditText;

    /**
     * Constructor.
     *
     * @param context Context.
     */
    public EasyMoneyEditText(Context context) {
        super(context);
        initView(context, null);
    }

    /**
     * Constructor.
     *
     * @param context Context.
     * @param attrs AttributeSet.
     */
    public EasyMoneyEditText(Context context, AttrSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttrSet attrs) {
        // Setting Default Parameters
        currencySymbolEditText = Currency.getInstance(Locale.getDefault()).getSymbol();
        showCurrencyEditText = true;
        showCommasEditText = true;

        // Check for the attributes
        if (attrs != null) {
            // Attribute initialization
            String currency = AttrUtil.getStringValue(attrs,
                    AttrUtil.getString(context, ResourceTable.String_currency_symbol_edit_text), null);
            if (currency == null) {
                currency = Currency.getInstance(Locale.getDefault()).getSymbol();
            }
            setCurrency(currency);

            showCurrencyEditText = AttrUtil.getBooleanValue(attrs,
                    AttrUtil.getString(context, ResourceTable.String_show_currency_edit_text), true);
            showCommasEditText = AttrUtil.getBooleanValue(attrs,
                    AttrUtil.getString(context, ResourceTable.String_show_commas_edit_text), true);
        }

        // Add Text Watcher for Decimal formatting
        initTextWatchers();
    }

    private void initTextWatchers() {
        this.addTextObserver(new Text.TextObserver() {
            @Override
            public void onTextUpdated(String s, int i, int i1, int i2) {
                EasyMoneyEditText.this.removeTextObserver(this);
                setValue(s);
                EasyMoneyEditText.this.addTextObserver(this);
            }
        });
    }

    private void setValue(String valueEditText) {
        try {
            long longval;

            valueEditText = getValueString();
            longval = (Long.parseLong(valueEditText));
            String formattedString = getDecoratedStringFromNumber(longval);

            // setting text after format to EditText
            setText(formattedString);

        } catch (NumberFormatException nfe) {
            setText(valueEditText);
            String valEditText = getValueString();

            if (valEditText.equals("")) {
                long val = 0;
                setText(getDecoratedStringFromNumber(val));
            } else {
                // Some decimal number
                if (valEditText.contains(".")) {
                    if (valEditText.indexOf(".") == valEditText.length() - 1) {
                        // decimal has been currently put
                        String front = getDecoratedStringFromNumber(
                                Long.parseLong(valEditText.substring(0, valEditText.length() - 1)));
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

    private String getDecoratedStringFromNumber(long number) {
        String numberPattern = "#,###,###,###";
        String decoStr;

        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.getDefault());
        if (showCommasEditText && !showCurrencyEditText) {
            formatter.applyPattern(numberPattern);
        } else if (showCommasEditText) {
            formatter.applyPattern(currencySymbolEditText + " " + numberPattern);
        } else if (showCurrencyEditText) {
            formatter.applyPattern(currencySymbolEditText + " ");
        } else {
            decoStr = number + "";
            return decoStr;
        }
        decoStr = formatter.format(number);
        return decoStr;
    }

    private void setShowCurrency(boolean value) {
        showCurrencyEditText = value;
        setValue(getText());
    }

    /**
     * Get the value of the text without any commas and currency.
     * For example, if the edit text value is $ 1,34,000.60 then this method will return 134000.60
     *
     * @return A string of the raw value in the text field.
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
        return getText();
    }

    /**
     * Set the currency symbol for the edit text.
     *
     * @param newSymbol the new symbol of currency in string
     */
    public void setCurrency(String newSymbol) {
        currencySymbolEditText = newSymbol;
        setValue(getText());
    }

    /**
     * Set the currency symbol for the edit text.
     *
     * @param locale the locale of new symbol. (Default is Locale)
     */
    public void setCurrency(Locale locale) {
        setCurrency(Currency.getInstance(locale).getSymbol());
    }

    /**
     * Set the currency symbol for the edit text.
     *
     * @param currency the currency object of new symbol. (Defaul is Locale.Default)
     */
    public void setCurrency(Currency currency) {
        setCurrency(currency.getSymbol());
    }

    /**
     * Whether currency is shown in the text or not. (Default is true)
     *
     * @return true if the currency is shown otherwise false.
     */
    public boolean isShowCurrency() {
        return showCurrencyEditText;
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
        showCommasEditText = true;
        setValue(getText());
    }

    /**
     * Hides the commas in the text. (Default is shown).
     */
    public void hideCommas() {
        showCommasEditText = false;
        setValue(getText());
    }
}
