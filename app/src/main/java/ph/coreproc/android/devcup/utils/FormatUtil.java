package ph.coreproc.android.devcup.utils;

import java.text.DecimalFormat;

/**
 * Created by johneris on 8/15/15.
 */
public class FormatUtil {

    private static DecimalFormat decimalFormat;
    private static DecimalFormat pesoFormat;

    static {
        decimalFormat = new DecimalFormat("#0.##");

        pesoFormat = new DecimalFormat("PHP #,##0.00");
        pesoFormat.setGroupingSize(3);
    }

    public static String toPesoFormat(double d) {
        return pesoFormat.format(d);
    }

    public static String toDecimalFormat(double d) {
        if(d < 0.09) {
            return new DecimalFormat("#0.####").format(d);
        }
        return decimalFormat.format(d);
    }

}
