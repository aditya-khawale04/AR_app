package com.example.myapplication;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.Color;
import com.google.ar.sceneform.rendering.MaterialFactory;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ShapeFactory;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class ARActivity extends AppCompatActivity {

    private ArFragment arFragment;
    private ModelRenderable shapeRenderable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);

        // Properly cast the fragment
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.arFragment);
        if (fragment instanceof ArFragment) {
            arFragment = (ArFragment) fragment;
        } else {
            Toast.makeText(this, "AR Fragment not found or cast failed", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // Create red material and cube shape
        MaterialFactory.makeOpaqueWithColor(this, new Color(android.graphics.Color.RED))
                .thenAccept(material -> {
                    shapeRenderable = ShapeFactory.makeCube(
                            new com.google.ar.sceneform.math.Vector3(0.1f, 0.1f, 0.1f), // size
                            new com.google.ar.sceneform.math.Vector3(0f, 0.1f, 0f),     // center
                            material
                    );
                });

        // Add cube on plane tap
        arFragment.setOnTapArPlaneListener((HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
            if (shapeRenderable == null) return;

            Anchor anchor = hitResult.createAnchor();
            AnchorNode anchorNode = new AnchorNode(anchor);
            anchorNode.setParent(arFragment.getArSceneView().getScene());

            TransformableNode cubeNode = new TransformableNode(arFragment.getTransformationSystem());
            cubeNode.setParent(anchorNode);
            cubeNode.setRenderable(shapeRenderable);
            cubeNode.select();
        });
    }
}
