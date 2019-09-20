package vip.ruoyun.permission.pro.check;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import vip.ruoyun.permission.pro.R;

/**
 * Created by ruoyun on 2019-06-25.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class ContactsChecker {

    private static final String TAG = "ContactsChecker";
    public static final String PERMISSION_NAME = "联系人";

    public static final int PERMISSION_ICON_RES = R.drawable.miss_permission_ic_contacts;

    public static final String[] NEED_PERMISSION = {
            Manifest.permission.READ_CONTACTS,//
            Manifest.permission.WRITE_CONTACTS,//
            Manifest.permission.GET_ACCOUNTS,//
    };

    private static final String TAG_NUMBER = "1";

    private static boolean checkWriteContacts(Activity activity) throws Exception {
        if (checkReadContacts(activity)) {
            // write some info
            ContentValues values = new ContentValues();
            ContentResolver contentResolver = activity.getContentResolver();
            Uri rawContactUri = contentResolver.insert(ContactsContract.RawContacts
                    .CONTENT_URI, values);
            long rawContactId = ContentUris.parseId(rawContactUri);
            values.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds
                    .StructuredName.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
            values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, TAG);
            values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, TAG_NUMBER);
            contentResolver.insert(ContactsContract.Data.CONTENT_URI, values);

            // delete info
            Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
            ContentResolver resolver = activity.getContentResolver();
            Cursor cursor = resolver.query(uri, new String[]{ContactsContract.Contacts.Data._ID},
                    "display_name=?", new String[]{TAG}, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int id = cursor.getInt(0);
                    resolver.delete(uri, "display_name=?", new String[]{TAG});
                    uri = Uri.parse("content://com.android.contacts/data");
                    resolver.delete(uri, "raw_contact_id=?", new String[]{id + ""});
                }
                cursor.close();
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * read contacts, {@link Manifest.permission#READ_CONTACTS}
     *
     * @param activity
     * @return true if success
     * @throws Exception
     */
    private static boolean checkReadContacts(Activity activity) throws Exception {
        Cursor cursor = activity.getContentResolver().query(ContactsContract.CommonDataKinds.Phone
                .CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            if (isNumberIndexInfoIsNull(cursor, cursor.getColumnIndex(ContactsContract.CommonDataKinds
                    .Phone.NUMBER))) {
                cursor.close();
                return false;
            }
            cursor.close();
            return true;
        } else {
            return false;
        }
    }

    private static boolean isNumberIndexInfoIsNull(Cursor cursor, int numberIndex) {
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                return TextUtils.isEmpty(cursor.getString(numberIndex));
            }
            return false;
        } else {
            return true;
        }
    }
}
