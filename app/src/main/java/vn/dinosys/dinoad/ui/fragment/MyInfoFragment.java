package vn.dinosys.dinoad.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.OnClick;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;
import vn.dinosys.dinoad.util.BitmapUtil;

/**
 * Created by huutai.
 * Since: 8/12/16 on 11:26 AM
 * Project: DinoAd
 */
public class MyInfoFragment extends BaseFragment {

    public static MyInfoFragment newInstance() {
        MyInfoFragment myInfoFragment = new MyInfoFragment();
        return myInfoFragment;
    }

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.textEmail)
    TextView mTextEmail;

    @BindView(R.id.imgAvatar)
    ImageView mImgAvatar;

    @BindView(R.id.textGender)
    TextView mTextGender;

    @BindView(R.id.textBirthday)
    TextView mTextBirthday;

    static final int REQUEST_TAKE_PHOTO = 1;

    private static final int PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 11;

    private DatePickerDialog mBirthdayPickerDialog;

    private AlertDialog.Builder mGenderDialog;

    private long mBirthday;

    private final float TEXT_ALPHA = 0.6f;
    private final float MAX_SIZE_AVATAR = 512;

    private File mCurrentPhotoFile;

    private Bitmap mBitmapAvatar;

    private Handler mHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_info, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();
        setHasOptionsMenu(true);

        initToolbar();
        setUpUI();
    }

    private void initToolbar() {
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = appCompatActivity.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.my_info);
        }
    }

    private void setUpUI() {
        initDatePicker();
        initGenderPicker();
    }

    private void initDatePicker() {
        Calendar calendar = new GregorianCalendar(TimeZone.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

//        if (mUser.getBirthday() != null) {
//            mBirthday = mUser.getBirthday();
//            calendar.setTimeInMillis(mUser.getBirthday() * 1000);
//            mTextBirthday.setText(sdf.format(calendar.getTime()));
//            mBirthdayPickerDialog = new DatePickerDialog(getActivity(), mBirthdaySetListener,
//                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//        } else {
            mBirthdayPickerDialog = new DatePickerDialog(getActivity(), mBirthdaySetListener, 1990, 1, 1);
//        }

    }

    private DatePickerDialog.OnDateSetListener mBirthdaySetListener = (view, year, monthOfYear, dayOfMonth) -> {
        Log.d("TAG", "birthday set ");
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        mTextBirthday.setText(sdf.format(calendar.getTime()));
        mBirthday = calendar.getTimeInMillis() / 1000;
    };

    private void initGenderPicker() {
        mGenderDialog = new AlertDialog.Builder(getContext());
        mGenderDialog.setTitle(getResources().getString(R.string.str_gender));
        String[] genders = getResources().getStringArray(R.array.str_gender);
        mGenderDialog.setItems(genders, (dialog, which) -> {
//            dialog.dismiss();
            mTextGender.setText(genders[which]);
        });
    }

    @OnClick(R.id.llPhoto)
    public void onClickAvatar(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.str_select_avatar));
        String[] items = {getString(R.string.str_take_photo), getString(R.string.str_pick_photo)};
        builder.setItems(items, (dialog, which) -> {
//            dialog.dismiss();
            switch (which) {
                case 0:
                    if (checkPermission())
                        dispatchTakePictureIntent();
                    break;
                case 1:
                    Crop.pickImage(getContext(), this);
                    break;
            }
        });
        builder.show();

    }

    @OnClick(R.id.llGender)
    public void onClickGender(View view) {
        mGenderDialog.show();
    }

    @OnClick(R.id.llBirthday)
    public void onClickBirthday(View view) {
        mBirthdayPickerDialog.show();
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
            // Create the File where the photo should go
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES);
            File photoFile = null;
            try {
                photoFile = File.createTempFile(
                        imageFileName,  /* prefix */
                        ".jpg",         /* suffix */
                        storageDir      /* directory */
                );
            } catch (IOException pE) {
                pE.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                mCurrentPhotoFile = photoFile;
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                Log.d("TAG", "photo file: " + Uri.fromFile(getContext().getFilesDir()));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("TAG", "activity result: " + requestCode + ", " + resultCode);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == Activity.RESULT_OK) {
            Uri destination = Uri.fromFile(new File(getContext().getCacheDir(), "cropped.jpg"));
            Crop.of(Uri.fromFile(mCurrentPhotoFile), destination).asSquare().start(getContext(), this);
        } else if (requestCode == Crop.REQUEST_PICK && resultCode == Activity.RESULT_OK) {
            Log.d("TAG", "pick image: " + data.getData().toString());
            Uri destination = Uri.fromFile(new File(getContext().getCacheDir(), "cropped.jpg"));
            Crop.of(data.getData(), destination).asSquare().start(getContext(), this);
        } else if (requestCode == Crop.REQUEST_CROP && resultCode == Activity.RESULT_OK) {
            String imgPath = Crop.getOutput(data).toString().replace("file://", "");
            new Thread(() -> {
                mBitmapAvatar = BitmapUtil.decodeBitmapFromURI(imgPath, 2);
                if (mBitmapAvatar.getWidth() > MAX_SIZE_AVATAR || mBitmapAvatar.getHeight() > MAX_SIZE_AVATAR)
                    mBitmapAvatar = BitmapUtil.scaleDown(mBitmapAvatar, MAX_SIZE_AVATAR, true);
                mHandler.post(() -> mImgAvatar.setImageBitmap(mBitmapAvatar));
            }).start();

            if (mCurrentPhotoFile != null && mCurrentPhotoFile.exists()) {
                mCurrentPhotoFile.delete();
                Log.d("TAG", "delete current photofile");
            }
//            uploadAvatar(imgPath);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            getActivity().finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    dispatchTakePictureIntent();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    Toast.makeText(getActivity(), R.string.permission_denied, Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public boolean checkPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {


            // No explanation needed, we can request the permission.

            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.

            return false;
        }

        return true;
    }
}
