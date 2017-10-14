package es.source.code.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.source.code.model.User;

import static android.Manifest.permission.READ_CONTACTS;


public class LoginOrRegister extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    //set resultCode for onActivityResult() for MainScreen.java
    private static final int RETURN = 228;
    private static final int SUCCESS = 229;
    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "zhoutao:123456", "admin:123"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;
    private User loginUser = null;
    // UI references.
    @BindView(R.id.userName)
    AutoCompleteTextView mUserNameView;

    @BindView(R.id.progressbar)
    ProgressBar mProgressBar;

    @BindView(R.id.back)
    TextView mBack;

    @BindView(R.id.password)
    EditText mPasswordView;

    @BindView(R.id.userName_sign_in_button)
    Button mUserNameSignInButton;

    @BindView(R.id.userName_register_button)
    Button mUserNameRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_or_register);
        ButterKnife.bind(this);
        // Set up the login form.
        populateAutoComplete();
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);
        mBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent();
                intentBack.putExtra("fromLogin", "Return");
                setResult(RETURN, intentBack);
                finish();
            }
        });

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mUserNameSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        mUserNameRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });

    }

    //auto complete for username
    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mUserNameView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid userName, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */


    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }
        // Store values at the time of the login attempt.
        String userName = mUserNameView.getText().toString();
        String password = mPasswordView.getText().toString();

        if (checkInput()) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mProgressBar.setVisibility(ProgressBar.VISIBLE);
            new Thread() {
                @Override
                public void run() {
                    int i = 0;
                    while (i < 100) {
                        i++;
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mProgressBar.setProgress(i);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                        }
                    });
                }
            }.start();
            mAuthTask = new UserLoginTask(userName, password);
            mAuthTask.execute((Void) null);
        }
    }
    private void attemptRegister(){
        // Store values at the time of the login attempt.
        String userName = mUserNameView.getText().toString();
        String password = mPasswordView.getText().toString();
        if (checkInput()){

        }else {
            loginUser = new User();
            loginUser.setOldUser(false);
            loginUser.setUserName(userName);
            loginUser.setPassword(password);
            Intent intentRegister = new Intent();
            intentRegister.putExtra("fromLogin", "RegisterSuccess");
            Bundle bundleRegister = new Bundle();
            bundleRegister.putSerializable("userModel",loginUser);
            intentRegister.putExtras(bundleRegister);
            setResult(SUCCESS, intentRegister);
            finish();
        }
    }

    //checkInput() is to check the username and password's format
    private boolean checkInput(){
        boolean checkResult = false;
        // Reset errors.
        mUserNameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String userName = mUserNameView.getText().toString();
        String password = mPasswordView.getText().toString();

        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required), null);
            focusView = mPasswordView;
            checkResult = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_content), null);
            focusView = mPasswordView;
            checkResult = true;
        }
        // Check for a valid userName address.
        if (TextUtils.isEmpty(userName)) {
            mUserNameView.setError(getString(R.string.error_field_required), null);
            focusView = mUserNameView;
            checkResult = true;
        } else if (!isPasswordValid(userName)) {
            mUserNameView.setError(getString(R.string.error_invalid_content), null);
            focusView = mUserNameView;
            checkResult = true;
        }
        if (checkResult){
            focusView.requestFocus();
        }

        return checkResult;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        Pattern p = Pattern.compile("^[A-Za-z0-9]+", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(password);
        return m.matches();
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only userName addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary userName addresses first. Note that there won't be
                // a primary userName address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> userNames = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            userNames.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addUserNamesToAutoComplete(userNames);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addUserNamesToAutoComplete(List<String> userNameAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginOrRegister.this,
                        android.R.layout.simple_dropdown_item_1line, userNameAddressCollection);

        mUserNameView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mUserName;
        private final String mPassword;

        UserLoginTask(String userName, String password) {
            mUserName = userName;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            boolean flag = false;
            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }


            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mUserName) && pieces[1].equals(mPassword)) {
                    flag = true;
                }
            }
            // TODO: register the new account here.
            return flag;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            if (success) {
                User loginUser = new User();
                loginUser.setUserName(mUserName);
                loginUser.setPassword(mPassword);
                loginUser.setOldUser(true);
                Intent intentLogin = new Intent();
                intentLogin.putExtra("fromLogin", "LoginSuccess");
                Bundle bundleLogin = new Bundle();
                bundleLogin.putSerializable("userModel",loginUser);
                intentLogin.putExtras(bundleLogin);
                setResult(SUCCESS, intentLogin);
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }
}

