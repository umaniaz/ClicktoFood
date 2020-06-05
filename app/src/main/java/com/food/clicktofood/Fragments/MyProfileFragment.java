package com.food.clicktofood.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;
import com.food.clicktofood.Adapter.FileUtil;
import com.food.clicktofood.Adapter.InternalDataProvider;
import com.food.clicktofood.R;
import com.food.clicktofood.SessionData.SessionData;
import com.food.clicktofood.SingleImageFragmentForNormalView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import id.zelory.compressor.Compressor;
import okhttp3.MultipartBody;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final String TAG = "ctf_"+this.getClass().getSimpleName();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View myview;
    EditText name, password;
    TextView phone, email;
    SessionData sessionData;
    ImageView imgCat;

    MultipartBody.Part profile_image;
    File imageFIle, actualImage, compressedImage;
    String FilePathStr = "";

    public static MyProfileFragment newInstance() {
        MyProfileFragment fragment = new MyProfileFragment();
        return fragment;
    }

    public MyProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyProfileFragment newInstance(String param1, String param2) {
        MyProfileFragment fragment = new MyProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_my_profile, container, false);
        name = (EditText)myview.findViewById(R.id.etName);
        password = (EditText)myview.findViewById(R.id.etPassword);
        phone = (TextView)myview.findViewById(R.id.tvPhone);
        email = (TextView)myview.findViewById(R.id.tvEmail);
        sessionData = new SessionData(getActivity());
        name.setText(sessionData.getUserDataModel().getData().getMember().get(0).getFullName());
        phone.setText(sessionData.getUserDataModel().getData().getMember().get(0).getPhoneNo());
        email.setText(sessionData.getUserDataModel().getData().getMember().get(0).getEmail());
        password.setText(sessionData.getUserDataModel().getData().getMember().get(0).getPassword());

        FilePathStr = "";
        imgCat = (ImageView)myview.findViewById(R.id.imgCat);
        imgCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                ad.setCancelable(true);
                ad.setTitle("Upload Image");
                ad.setItems(new String[]{"Take Picture", "Select from gallery"}, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            ImagePicker.cameraOnly().start(MyProfileFragment.this);
                            break;
                        case 1:
                            ImagePicker.create(MyProfileFragment.this) // Activity or Fragment
                                    .returnMode(ReturnMode.NONE) // set whether pick and / or camera action should return immediate result or not.
                                    .folderMode(true) // folder mode (false by default)
                                    .toolbarFolderTitle("Folder") // folder selection title
                                    .toolbarImageTitle("Tap to select") // image selection title
                                    .toolbarArrowColor(Color.BLACK) // Toolbar 'up' arrow color
                                    .includeVideo(false) // Show video on image picker
                                    .single() // single mode
                                    .multi() // multi mode (default mode)
                                    .limit(1) // max images can be selected (99 by default)
                                    .showCamera(false) // show camera or not (true by default)
                                    .imageDirectory("Camera") // directory name for captured image  ("Camera" folder by default)
                                    .origin(null) // original selected images, used in multi mode
                                    .exclude(null) // exclude anything that in image.getPath()
                                    .enableLog(false) // disabling log
                                    .start(); // start image picker activity with request code
                            break;
//                        case 2:
//                            File f = new File(FilePathStr);
//                            InternalDataProvider.getInstance().setImageFile(f);
//                            SingleImageFragmentForNormalView singleImageFragment = SingleImageFragmentForNormalView.newInstance();
//                            singleImageFragment.show(getActivity().getSupportFragmentManager(), "Hello");
//                            break;
                    }
                });
                ad.show();
            }
        });

        Picasso.get()
                .load(sessionData.getUserDataModel().getData().getMember().get(0).getPicture())
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.person_placeholder)
                .error(R.drawable.person_placeholder)
                .into(imgCat);

        return myview;
    }

    //----------- image processing ----------------------

    @Override
    public void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            List<Image> images = ImagePicker.getImages(data);
//          Image image = ImagePicker.getFirstImageOrNull(data);
            //Log.d(TAG, "image path "+images.get(0).getPath());
            FilePathStr = images.get(0).getPath();
            Bitmap selectedImage = BitmapFactory.decodeFile(FilePathStr);
            //imgselectedImage.setImageBitmap(selectedImage);

            //---------- check size -------------
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//            byte[] imageInByte = stream.toByteArray();
//            long sizeOfImage = imageInByte.length;
//            Log.d(TAG, "previous image size: "+sizeOfImage);
            //-----------------------------------

            //----- image compress
            try {

                //actualImage = FileUtil.from(getActivity(), data.getData());
                actualImage = FileUtil.from(getActivity(), getImageUri(getActivity(), selectedImage));


                Bitmap compressedImageBitmap = new Compressor(getActivity()).compressToBitmap(actualImage);
                imgCat.setImageBitmap(compressedImageBitmap);
                new fileFromBitmap(compressedImageBitmap, getActivity()).execute();

                //------ check file size ------
//                ByteArrayOutputStream streams = new ByteArrayOutputStream();
//                compressedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, streams);
//                byte[] imageInBytes = streams.toByteArray();
//                long sizeOfImages = imageInBytes.length;
//                Log.d(TAG, "converted image size: "+sizeOfImages);
                //----------------------
            }catch (Exception e){
                Log.d(TAG, "Exception: "+e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public class fileFromBitmap extends AsyncTask<Void, Integer, String> {
        File file;
        Context context;
        Bitmap bitmap;
        String path_external = Environment.getExternalStorageDirectory() + File.separator + "temporary_file.jpg";

        public fileFromBitmap(Bitmap bitmap, Context context) {
            this.bitmap = bitmap;
            this.context= context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before executing doInBackground
            // update your UI
            // exp; make progressbar visible
        }

        @Override
        protected String doInBackground(Void... params) {

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            file = new File(FilePathStr);
            try {
                FileOutputStream fo = new FileOutputStream(file);
                fo.write(bytes.toByteArray());
                fo.flush();
                fo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // back to main thread after finishing doInBackground
            // update your UI or take action after
            // exp; make progressbar gone

            imageFIle = file;

        }
    }

    //---------------------------------------------------

    //==============================================


    @Override
    public void onStart() {
        super.onStart();
        sessionData.setAppState(false);
    }

    @Override
    public void onStop() {
        super.onStop();
        sessionData.setAppState(false);
    }
}
