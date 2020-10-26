package com.example.schedulecreator.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormater implements IDateFormaterUtil {
    @Override
    public String formatDateForEditText(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd / MM / YYYY");
        return format.format( date );
    }



}
