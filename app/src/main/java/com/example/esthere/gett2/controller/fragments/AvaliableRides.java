package com.example.esthere.gett2.controller.fragments;

import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.DialogInterface;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.esthere.gett2.R;
import com.example.esthere.gett2.model.backend.IAction;
import com.example.esthere.gett2.model.datasource.Globals;
import com.example.esthere.gett2.model.entities.Driver;
import com.example.esthere.gett2.model.entities.Ride;
import com.example.esthere.gett2.utils.Dialogs;
import com.example.esthere.gett2.utils.ManageLocation;

import java.util.ArrayList;


public class AvaliableRides extends Fragment {

    protected ArrayList<Ride> rides;

    public AvaliableRides() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_avaliable_rides, container, false);
        final ListView listOpenTravels = (ListView) view.findViewById(R.id.listOpenTravels);

        try {
            Globals.backend.openRides(new IAction<ArrayList<Ride>>() {
                @Override
                public void onSuccess(ArrayList<Ride> obj) {
                    rides = obj;
                    try {
                        if (rides != null && getActivity() != null) {
                            ArrayAdapter<Ride> adapter = new ArrayAdapter<Ride>(getActivity(), R.layout.ride, rides) {
                                @NonNull
                                @Override
                                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                    if (convertView == null)
                                        convertView = View.inflate(getActivity(), R.layout.ride, null);

                                    final EditText name = (EditText) convertView.findViewById(R.id.name);
                                    final EditText tel = (EditText) convertView.findViewById(R.id.tel);
                                    final EditText email = (EditText) convertView.findViewById(R.id.email);
                                    EditText source = (EditText) convertView.findViewById(R.id.source);
                                    EditText target = (EditText) convertView.findViewById(R.id.target);
                                    Button claim = (Button) convertView.findViewById(R.id.claim);
                                    Button unclaim = (Button) convertView.findViewById(R.id.unclaim);
                                    //!!!!!!!!!!!!!!!!!!!
                                    Button addToContant = (Button) convertView.findViewById(R.id.addContact);
                                    EditText start = (EditText) convertView.findViewById(R.id.start);
                                    if (rides.size() > 0) {
                                        claim.setTag(rides.get(position).getKey());
                                        //Globals.customer = rides.get(position).getCustomer();
                                        claim.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                String key = (String) ((Button) view).getTag();
                                                Globals.backend.Claim(key, Globals.driver.getEmail(), new IAction<Boolean>() {
                                                    @Override
                                                    public void onSuccess(Boolean obj) {
                                                        Dialogs.Dialog(getActivity(), getString(R.string.FIREBASE), getString(R.string.CLAIMED), getString(R.string.BUTTON_CLOSE));
                                                        return;
                                                    }

                                                    @Override
                                                    public void onFailure(Exception ex) {
                                                        Dialogs.Dialog(getActivity(), getString(R.string.FIREBASE), ex.getMessage(), getString(R.string.BUTTON_CLOSE));
                                                        return;
                                                    }
                                                });

                                            }
                                        });
                                        addToContant.setTag(rides.get(position).getKey());
                                        addToContant.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                addContact(name.getText().toString(), tel.getText().toString(), email.getText().toString());
//                                                try {
//                                                    SaveContact(name.getText().toString(), tel.getText().toString(), email.getText().toString());
//                                                } catch (OperationApplicationException e) {
//                                                    e.printStackTrace();
//                                                } catch (RemoteException e) {
//                                                    e.printStackTrace();
//                                                }
                                                //String key = (String) ((Button) view).getTag();

                                                //-- Add New Contact using Content Provider
//                                                ContentValues values = new ContentValues();
//                                                values.put(ContactsContract.Data.RAW_CONTACT_ID, 001);
//                                                values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
//                                                values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, tel.toString());
//                                                values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_CUSTOM);
//                                                values.put(ContactsContract.CommonDataKinds.Phone.LABEL, "Nirav");
//                                                Uri dataUri = getActivity().getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
//
//                                                // Inser an empty contact.
//                                                ContentValues contentValues = new ContentValues();
//                                                Uri rawContactUri = getActivity().getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, contentValues);
//// Get the newly created contact raw id.
//                                                long ret = ContentUris.parseId(rawContactUri);
                                            }
                                        });

                                        unclaim.setVisibility(View.GONE);
                                        //addToContant.setVisibility(View.GONE);

                                        name.setText(rides.get(position).getCustomer().getName());
                                        tel.setText(rides.get(position).getCustomer().getTel());
                                        email.setText(rides.get(position).getCustomer().getEmail());
                                        source.setText(ManageLocation.addressFromLongLat(rides.get(position).getSourceLocation(), getActivity()));
                                        target.setText(ManageLocation.addressFromLongLat(rides.get(position).getTargetLocation(), getActivity()));
                                        //start.setText(rides.get(position).getRealLeavingTime().toString());
                                    }
                                    return convertView;
                                }
                            };
                            listOpenTravels.setAdapter(adapter);
                        }

                    } catch (Exception e) {
                        Log.w("GetTaxi2", "Error In Open Rides " + e.getMessage());
                    }
                }

                @Override
                public void onFailure(Exception exception) {
                }
            });

        } catch (Exception e) {

        }
        return view;
    }

    public void SaveContact(String name, String tel, String mail) throws OperationApplicationException, RemoteException {

        //Create a new contact entry!
        String szFullname = name;
        //https://developer.android.com/reference/android/provider/ContactsContract.RawContacts
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        int rawContactInsertIndex = ops.size();

        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());
        //INSERT NAME
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, szFullname) // Name of the person
                .withValue(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME, name) // Name of the person
                .withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name) // Name of the person
                .build());

        //INSERT MOBILE
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, tel) // Number of the person
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .build()); //

        //INSERT EMAIL
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Email.DATA, mail)
                .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                .build());

        // SAVE CONTACT IN BCR Structure
        Uri newContactUri = null;
        //PUSH EVERYTHING TO CONTACTS
//        try
//        {

        ContentProviderResult[] res = getActivity().getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        if (res != null && res[0] != null) {
            newContactUri = res[0].uri;

            //02-20 22:21:09 URI added contact:content://com.android.contacts/raw_contacts/612
            //Log.d(LOG_TAG, "URI added contact:"+ newContactUri);
        }
        //else Log.e(LOG_TAG, "Contact not added.");
        //}
//        catch (Exception ex)
//        {}
//        catch (RemoteException e)
//        {
//            // error
//            Log.e(LOG_TAG, "Error (1) adding contact.");
//            newContactUri = null;
//        }
//        catch (OperationApplicationException e)
//        {
//            // error
//            Log.e(LOG_TAG, "Error (2) adding contact.");
//            newContactUri = null;
//        }
//        Log.d(LOG_TAG, "Contact added to system contacts.");
//
//        if (newContactUri == null) {
//            //Log.e(LOG_TAG, "Error creating contact");
//
//        }

    }
    public void addContact(String name, String tel, String mail)
    {
        String DisplayName = name;
        String MobileNumber = tel;
        //String HomeNumber = "1111";
        //String WorkNumber = "2222";
        String emailID = mail;
        //String company = "bad";
        //String jobTitle = "abcd";

        ArrayList < ContentProviderOperation > ops = new ArrayList < ContentProviderOperation > ();

        ops.add(ContentProviderOperation.newInsert(
                ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());

        //------------------------------------------------------ Names
        if (DisplayName != null) {
            ops.add(ContentProviderOperation.newInsert(
                    ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(
                            ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                            DisplayName).build());
        }

        //------------------------------------------------------ Mobile Number
        if (MobileNumber != null) {
            ops.add(ContentProviderOperation.
                    newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, MobileNumber)
                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                            ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                    .build());
        }

//        //------------------------------------------------------ Home Numbers
//        if (HomeNumber != null) {
//            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
//                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//                    .withValue(ContactsContract.Data.MIMETYPE,
//                            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
//                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, HomeNumber)
//                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
//                            ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
//                    .build());
//        }

//        //------------------------------------------------------ Work Numbers
//        if (WorkNumber != null) {
//            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
//                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//                    .withValue(ContactsContract.Data.MIMETYPE,
//                            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
//                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, WorkNumber)
//                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
//                            ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
//                    .build());
//        }

        //------------------------------------------------------ Email
        if (emailID != null) {
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Email.DATA, emailID)
                    .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                    .build());
        }

//        //------------------------------------------------------ Organization
//        if (!company.equals("") && !jobTitle.equals("")) {
//            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
//                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//                    .withValue(ContactsContract.Data.MIMETYPE,
//                            ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
//                    .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, company)
//                    .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
//                    .withValue(ContactsContract.CommonDataKinds.Organization.TITLE, jobTitle)
//                    .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
//                    .build());
//        }

        // Asking the Contact provider to create a new contact
        try {
            getActivity().getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
