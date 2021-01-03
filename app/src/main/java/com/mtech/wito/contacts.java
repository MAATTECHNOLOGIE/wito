package com.mtech.wito;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.regex.Pattern;



public class contacts extends AppCompatActivity {
    // Regex de reseau Orange
    private static Pattern orange_1 = Pattern.compile("^[0-9]{1}[789].+[0-9]");
    private static Pattern orange_2 = Pattern.compile("^(\\+225)( |)([0-9]{1}[789])");
    private static Pattern orange_3 = Pattern.compile("^(00225)( |)([0-9]{1}[789])");
    private static Pattern orange_4 = Pattern.compile("^(225)( |)([0-9]{1}[789])") ;
    // Regex de reseau MTN
    private static Pattern mtn_1 = Pattern.compile("^[0-9]{1}[456].+[0-9]");
    private static Pattern mtn_2 = Pattern.compile("^(\\+225)( |)([0-9]{1}[456])");
    private static Pattern mtn_3 = Pattern.compile("^(00225)( |)([0-9]{1}[456])");
    private static Pattern mtn_4 = Pattern.compile("^(225)( |)([0-9]{1}[456])");
    // Regex reseau MOOV
    private static Pattern moov_1 = Pattern.compile("^[0-9]{1}[0123].+[0-9]");
    private static Pattern moov_2 = Pattern.compile("^(\\+225)( |)([0-9]{1}[0123])");
    private static Pattern moov_3 = Pattern.compile("^(00225)( |)([0-9]{1}[0123])");
    private static Pattern moov_4 = Pattern.compile("^(225)( |)([0-9]{1}[0123])");




    TextView tv_phonebook;
    // To store the phoneBook
    ArrayList<String> arrayList;
    private String cursor;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // Affichage de tous les contacts
        tv_phonebook = findViewById(R.id.tv_phonebook);
        // To initialize the memory to arrayList
        arrayList = new ArrayList<>();
        // give runtime permission for read contact
          //this problem comes in marshmallow or greater version
           //this problem only for dangerous permission like phonebook,sms,camera
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED)
           {
               // now request the permission
               requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},1);
           }else{
               // for lower than marsmallow version
                 //To get the phonebook
                   getContact();
                   
           }

        // Boutton de retour à la page d'acceuil
          // Initiate view's
          ImageButton bactohome = (ImageButton)findViewById(R.id.homebtn);
          bactohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contacts.this,MainActivity.class);
                startActivity(intent);
            }
          });




    }

    // Métode to get phone number
    private void getContact() {
        // To pass all phonebook to cursor
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        // To fetch all contact from cursor
        while (cursor.moveToNext())
        {
            // Pass the data into string  from cursor
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String mobile = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            // Now Add the data into arraylist
            arrayList.add(name + "=>" + mobile+"\n");

            // To attach the arrayList into textview
            tv_phonebook.setText(arrayList.toString());
        }
    }

    // Methode to matches Orange number
    public static  boolean isValidOrange(String mobile)
    {
        boolean res;

        if (orange_1.matcher(mobile).matches()==true)
        {
            res = true;
            return res;
        }
        else if (orange_2.matcher(mobile).matches()==true)
        {
            res = true;
            return res;
        }
        else if (orange_3.matcher(mobile).matches()==true)
        {
            res = true;
            return res;
        }
        else if (orange_4.matcher(mobile).matches()==true)
        {
            res = true;
            return res;
        }
        else
        {
            res = false;
            return res;
        }
    }
    //Methode to matches mtn number
    public static boolean isValidMtn(String mobile)
    {
        boolean res;

        if (mtn_1.matcher(mobile).matches()==true)
        {
            res = true;
            return res;
        }
        else if(mtn_2.matcher(mobile).matches()==true)
        {
            res = true;
            return res;
        }
        else if (mtn_3.matcher(mobile).matches()==true)
        {
            res = true;
            return res;
        }
        else  if(mtn_4.matcher(mobile).matches()==true)
        {
            res = true;
            return res;
        }
        else
        {
            res = false;
            return res;
        }
    }
    //Methode to matches moov number
    public static boolean isValidMoov(String mobile)
    {
        boolean res;
        if (moov_1.matcher(mobile).matches()==true)
        {
            res = true;
            return res;
        }
        else if(moov_2.matcher(mobile).matches()==true)
        {
            res = true;
            return res;
        }
        else if(moov_3.matcher(mobile).matches()==true)
        {
            res = true;
            return res;
        }
        else if(moov_4.matcher(mobile).matches()==true)
        {
            res = true;
            return res;
        }
        else
        {
            res = false;
            return res;
        }


    }




    // To get the output of runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1)
        {
           if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
           {
               // Now permission granted call function again
               getContact();
           }
        }
    }

    // Method to change the phone number
    public void setContact(View view) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED)
        {
            // now request the permission
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},1);
        }else{



            // To pass all to cursor
            Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            Context context = getApplicationContext();






            int duration = Toast.LENGTH_LONG;
            CharSequence text = null;
            // Les prefix
          
            String mobileF = null;

            while ((cursor.moveToNext()))
            {
                String mobile = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
             
                // Filtrage de numero
                  // Orange
                    if (isValidOrange(mobile))
                    {

                        if (orange_1.matcher(mobile).matches()==true)
                        {
                            mobileF = "07"+mobile;
                            //Modifier le numero de telephone
                            try {
                                // Create new intent
                                Intent insertIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
                                // set the MIME TYPE
                                insertIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                                // Sets the new contact name
                                insertIntent.putExtra(ContactsContract.Intents.Insert.NAME, name);
                                // Defines an array list to contain the ContentValues objects for each row
                                ArrayList contactData = new ArrayList<ContentValues>();
                                // Sets up the row as a ContentValues object
                                ContentValues phoneRow = new ContentValues();
                                // Specifies the MIME type for this data row (all data rows must be marked by their type)
                                phoneRow.put(
                                        ContactsContract.Data.MIMETYPE,
                                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
                                );
                                // Adds the phone number and its type to the row
                                phoneRow.put(ContactsContract.CommonDataKinds.Phone.NUMBER, mobileF);
                                // Adds the row to the array::phone
                                contactData.add(phoneRow);
                                // Sets up the row as a ContentValues object::nom
                                ContentValues nameRow = new ContentValues();
                                // Specifies the MIME type for this data row (all data rows must be marked by their type)
                                nameRow.put(
                                        ContactsContract.Data.MIMETYPE,
                                        ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE
                                );
                                // Adds the name and its type to the row
                                nameRow.put(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, name);
                                // Adds the row to the array
                                contactData.add(nameRow);
                                insertIntent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
                                //startActivity(insertIntent);

                                //mobileF = "07" + mobile;
                            }
                            catch (Exception e)
                            {
                                text = "erreur";
                            }


                            text = name+","+mobile+": Orange=> "+mobileF;
                        }
                        else if(orange_2.matcher(mobile).matches()==true)
                        {
                            String target = "+225";
                            String remp = "+225 07";
                            String processed = mobile.replace(target, remp);
                            processed.contains(remp);
                            text = name+","+mobile+": Orange=> "+processed;
                        }
                        else if(orange_3.matcher(mobile).matches()==true)
                        {
                            String target = "00225";
                            String remp = "00225 07";
                            String processed = mobile.replace(target, remp);
                            processed.contains(remp);
                            text = name+","+mobile+": Orange=> "+processed;
                        }
                        else
                        {
                            String target = "225";
                            String remp = "225 07";
                            String processed = mobile.replace(target, remp);
                            processed.contains(remp);
                            text = name+","+mobile+": Orange=> "+processed;
                        }



                    }
                    else if(isValidMtn(mobile))
                    {

                        text = name+","+mobile+": MTN";

                    }
                    else if (isValidMoov(mobile))
                    {

                        text = name+","+mobile+": moov";
                    }
                    else
                    {
                         text = name+","+mobile+": Autre";
                    }



                Toast toast = Toast.makeText(context,text,duration);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER,0,0);
                toast.show();
            }


        }


    }




}