package com.example.arapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Camera;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Scene scene;
    private Camera camera;
    CustomArFragment arFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        arFragment = (CustomArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);

        scene = arFragment.getArSceneView().getScene();
        camera = scene.getCamera();

        addDummytoScene();
//
//        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent)->{
//        Anchor anchor = hitResult.createAnchor();
//
//        ModelRenderable.builder()
//                .setSource(this, Uri.parse("Geishamasks.sfb"))
//                .build()
//                .thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable))
//                .exceptionally(throwable -> {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                    builder.setMessage(throwable.getMessage())
//                            .show();
//                    return null;
//                });
//        });
    }

    private void addDummytoScene() {
        ModelRenderable.builder()
                .setSource(this, Uri.parse("human_male.sfb"))
                .build()
                .thenAccept( renderable -> {
                    Node node =new Node();
                    TransformableNode transformableNode = new TransformableNode(arFragment.getTransformationSystem());
                    transformableNode.setParent(node);
                    transformableNode.setRenderable(renderable);
                    scene.addChild(node);

                    Random random = new Random();
                    node.setLocalPosition(new Vector3((float)0.00,(float)-5.00,(float) -10));
                });

    }
//
//    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {
//        AnchorNode anchorNode = new AnchorNode(anchor);
//        TransformableNode transformableNode = new TransformableNode(arFragment.getTransformationSystem());
//        transformableNode.setParent(anchorNode);
//        transformableNode.setRenderable(modelRenderable);
//        arFragment.getArSceneView().getScene().addChild(anchorNode);
//        transformableNode.select();
//    }
}
