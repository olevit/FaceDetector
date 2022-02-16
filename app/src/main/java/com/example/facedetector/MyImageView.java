package com.example.facedetector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.media.FaceDetector.Face;
import android.util.AttributeSet;
import android.widget.ImageView;



public class MyImageView extends androidx.appcompat.widget.AppCompatImageView {
    private int imageWidth, imageHeight;
    private int numberofFace = 5;
    private FaceDetector myFaceDetect;
    private FaceDetector.Face[] myFace;
    float myEyesDistance;
    int numberOfFaceDetected;
    Paint myPaint = new Paint();
    PointF myMidPoint = new PointF();
    Bitmap myBitmap;

    public MyImageView(Context context) {
        super(context);
        init();
    }

    public MyImageView(Context c, AttributeSet attrs) {
        super(c, attrs);
        init();
    }

    private void init() {
        BitmapFactory.Options BitmapFactoryOptionsbfo = new BitmapFactory.Options();
        BitmapFactoryOptionsbfo.inPreferredConfig = Bitmap.Config.RGB_565;
        myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sofi, BitmapFactoryOptionsbfo);//foto Sofi
        imageWidth = myBitmap.getWidth();
        imageHeight = myBitmap.getHeight();
        myFace = new FaceDetector.Face[numberofFace];
        myFaceDetect = new FaceDetector(imageWidth, imageHeight, numberofFace);
        numberOfFaceDetected = myFaceDetect.findFaces(myBitmap, myFace);
    }
    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawBitmap(myBitmap,0,0, null);
        myPaint.setColor(Color.BLUE);
        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(40);

        //draw many squares on faces
        /*for(int i = 0; i < numberOfFaceDetected; i++){
            Face face = myFace[i];

            face.getMidPoint(myMidPoint);
            myEyesDistance = face.eyesDistance();
            canvas.drawRect((int) (myMidPoint.x - myEyesDistance),
                    (int) (myMidPoint.y - myEyesDistance),
                    (int) (myMidPoint.x + myEyesDistance),
                    (int) (myMidPoint.y + myEyesDistance), myPaint);
        }*/



        //draw 1 square on fase
        Face face = myFace[0];
        face.getMidPoint(myMidPoint); // середина между глазами
        myEyesDistance = face.eyesDistance();
        canvas.drawPoint((int) myMidPoint.x, (int) myMidPoint.y,
                myPaint);//center point
        canvas.drawRect((int) (myMidPoint.x - myEyesDistance),
                (int) (myMidPoint.y - myEyesDistance),
                (int) (myMidPoint.x + myEyesDistance),
                (int) (myMidPoint.y + myEyesDistance), myPaint);//draw square*/

        // draw two circles on eyes
        /*int lefteyeX = (int) (myMidPoint.x - myEyesDistance / 2);
        int lefteyeY = (int) myMidPoint.y;

        int righteyeX = (int)(myMidPoint.x + myEyesDistance / 2);
        int righteyeY = (int) myMidPoint.y;

        canvas.drawCircle(lefteyeX, lefteyeY, 100.0f, myPaint);
        canvas.drawCircle(righteyeX, righteyeY, 100.0f, myPaint);*/
    }
}

