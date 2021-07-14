package com.techshiv.collegeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class UploadNewsAdmin extends AppCompatActivity {
    ImageView ImageUploadNews;
    EditText NewsUploadTile,newsDescriptionUpload;
    Button uploadNews,CloasButtonNewsUpload;
    Uri imageUri;
    String MyimageUrl;
    StorageTask uploadTask;
    StorageReference mStorageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_news_admin);
        this.setTitle("Upload News");
        ImageUploadNews=findViewById(R.id.ImageUploadNews);
        NewsUploadTile=findViewById(R.id.NewsUploadTile);
        newsDescriptionUpload=findViewById(R.id.newsDescriptionUpload);
        uploadNews=findViewById(R.id.newsUploadButton);
        CloasButtonNewsUpload=findViewById(R.id.CloasButtonNewsUpload);
        mStorageReference= FirebaseStorage.getInstance().getReference("Posts");

        CloasButtonNewsUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UploadNewsAdmin.this,AdminPanel.class));
                finish();
            }
        });
        uploadNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadNews();
            }
        });
        CropImage.activity()
                .setAspectRatio(1,1)
                .start(UploadNewsAdmin.this);

    }
    private String getFileExtenion(Uri uri)
    {
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    private  void uploadNews(){
        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("processing");
        progressDialog.show();

        if(imageUri !=null)
        {
            StorageReference filerefrence=mStorageReference.child(System.currentTimeMillis()+"."+
                    getFileExtenion(imageUri));
            uploadTask=filerefrence.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if(!task.isSuccessful()){
                        throw  task.getException();
                    }
                    return filerefrence.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful())
                    {
                    Uri downloadUri=task.getResult();
                    MyimageUrl=downloadUri.toString();
                        Calendar calendar=Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEEE ,dd-mm-yyyy hh:mm:ss a");
                        String datetime =simpleDateFormat.format(calendar.getTime());
                        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Posts");
                        String PostId=reference.push().getKey();

                        HashMap<String,Object> hashMap=new HashMap<>();
                        hashMap.put("PostId",PostId);
                        hashMap.put("PostImage",MyimageUrl);
                        hashMap.put("News",newsDescriptionUpload.getText().toString());
                        hashMap.put("NewsTitle",NewsUploadTile.getText().toString());
                        hashMap.put("NewsTimeDate",datetime);
                        hashMap.put("Author", FirebaseAuth.getInstance().getCurrentUser().getUid());

                        reference.child(PostId).setValue(hashMap);
                         progressDialog.dismiss();
                        Toast.makeText(UploadNewsAdmin.this, "news upload!!!", Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(UploadNewsAdmin.this,AdminPanel.class));
                         finish();


                    }
                    else{
                        Toast.makeText(UploadNewsAdmin.this, "Failed!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UploadNewsAdmin.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(this, "No Image Selected", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
        CropImage.ActivityResult result=CropImage.getActivityResult(data);
        imageUri=result.getUri();
            ImageUploadNews.setImageURI(imageUri);
        }
        else{
            Toast.makeText(this, "Something went Wrong!!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(UploadNewsAdmin.this,AdminPanel.class));
            finish();
        }
        }
    }
