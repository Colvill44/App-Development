package com.example.inclass07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements EditFragment.EditFragmentListener, ContactsFragment.ContactsListener, CreateContactFragment.CreateContactListener {
Contacts MySelectedContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new ContactsFragment())
                .commit();
    }

    @Override
    public void gotoCreateContact() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new CreateContactFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoEditContact(Contacts contact) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new EditFragment())
                .addToBackStack(null)
                .commit();
        MySelectedContact =contact;
    }


    @Override
    public void gotoContacts() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new ContactsFragment())
                .commit();

    }

    @Override
    public void gotoEdit() {

    }

    @Override
    public Contacts onGetSelectedContact() {
        return MySelectedContact;
    }

    @Override
    public void gotoContactsAfterUpdate() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new ContactsFragment())
                .commit();
    }
/*
    @Override
    public Contacts OnGetEditContact() {
      return MySelectedContact;
      }
 */
}