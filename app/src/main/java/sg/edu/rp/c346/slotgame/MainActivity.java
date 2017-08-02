package sg.edu.rp.c346.slotgame;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;
import android.os.Handler;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    Button b_roll,btnShare;

    ImageView image1, image2, image3;

    Random r;
    int img1, img2, img3;

    ShareDialog shareDialog;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
// To generate development release hash key for facebook

//        PackageInfo info;
//
//        try {
//            info = getPackageManager().getPackageInfo("sg.edu.rp.c346.slotgame", PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md;
//                md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                String something = new String(Base64.encode(md.digest(), 0));
//                //String something = new String(Base64.encodeBytes(md.digest()));
//                Log.e("hash key", something);
//            }
//        } catch (PackageManager.NameNotFoundException e1) {
//            Log.e("name not found", e1.toString());
//        } catch (NoSuchAlgorithmException e) {
//            Log.e("no such an algorithm", e.toString());
//        } catch (Exception e) {
//            Log.e("exception", e.toString());
//        }


        r = new Random();

        b_roll = (Button) findViewById(R.id.b_roll);
        btnShare = (Button) findViewById(R.id.btnShare);

        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);


        shareDialog = new ShareDialog(this);  // intialize facebook shareDialog.

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle("Jackpot")
                            .setImageUrl(Uri.parse("https://s-media-cache-ak0.pinimg.com/originals/35/9d/c9/359dc97fee1915d96f6859fe29e6efca.jpg"))
                            .setContentDescription("You have scored a jackpot")
                            .setContentUrl(Uri.parse("http://www.google.com"))
                            .build();

                    shareDialog.show(linkContent);  // Show facebook ShareDialog
                }


            }
        });





        b_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //animate first image
                image1.setImageResource(R.drawable.anim);
                final AnimationDrawable image1anim = (AnimationDrawable) image1.getDrawable();
                image1anim.start();

                image2.setImageResource(R.drawable.anim);
                final AnimationDrawable image2anim = (AnimationDrawable) image2.getDrawable();
                image2anim.start();

                image3.setImageResource(R.drawable.anim);
                final AnimationDrawable image3anim = (AnimationDrawable) image3.getDrawable();
                image3anim.start();

                //stop the animation and apply the images
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        image1anim.stop();
                        image2anim.stop();
                        image3anim.stop();

                        setImages();
                        getScore();
                    }

                }, 500);
            }

            ;

        });


    }

    public void setImages() {
        //randomize the images
        img1 = r.nextInt(5) + 1;
        img2 = r.nextInt(5) + 1;
        img3 = r.nextInt(5) + 1;


        //set first image
        switch (img1) {
            case 1:
                image1.setImageResource(R.drawable.kappa);
                break;
            case 2:
                image1.setImageResource(R.drawable.kreygasm);
                break;
            case 3:
                image1.setImageResource(R.drawable.lul);
                break;
            case 4:
                image1.setImageResource(R.drawable.wutface);
                break;
            case 5:
                image1.setImageResource(R.drawable.trihard);
                break;
        }


        //set second image
        switch (img2) {
            case 1:
                image2.setImageResource(R.drawable.kappa);
                break;
            case 2:
                image2.setImageResource(R.drawable.kreygasm);
                break;
            case 3:
                image2.setImageResource(R.drawable.lul);
                break;
            case 4:
                image2.setImageResource(R.drawable.wutface);
                break;
            case 5:
                image2.setImageResource(R.drawable.trihard);
                break;
        }


        //set third image
        switch (img3) {
            case 1:
                image3.setImageResource(R.drawable.kappa);
                break;
            case 2:
                image3.setImageResource(R.drawable.kreygasm);
                break;
            case 3:
                image3.setImageResource(R.drawable.lul);
                break;
            case 4:
                image3.setImageResource(R.drawable.wutface);
                break;
            case 5:
                image3.setImageResource(R.drawable.trihard);
                break;
        }


    }

    public void getScore() {
        // 3 of the same image
        if (img1 == img2 && img2 == img3) {
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.audio1);
            mp.start();
            Toast.makeText(this, "JACKPOT!!! Share your result by clicking on the share button", Toast.LENGTH_LONG).show();
        }
        // 2 of the same image
        if (img1 == img2 || img2 == img3 || img1 == img3) {
            Toast.makeText(this, "NICE TRY!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // show menu when menu button is pressed
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // display a message when a button was pressed
        if (item.getItemId() == R.id.logout) {
            LoginManager.getInstance().logOut();
            Intent login = new Intent(MainActivity.this, login.class);
            startActivity(login);
        }
        return true;
    }







}



