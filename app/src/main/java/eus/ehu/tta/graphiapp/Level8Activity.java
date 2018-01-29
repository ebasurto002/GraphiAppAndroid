package eus.ehu.tta.graphiapp;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import eus.ehu.tta.graphiapp.Levels.Nivel8;

public class Level8Activity extends LevelBaseActivity<Nivel8> {

    private static final String ISWORDVISIBLE = "isWordVisible";
    boolean [] isWordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_level8,frameLayout,true);

        if (pin != null)
        {
            Button button = findViewById(R.id.levelBackButton);
            button.setEnabled(false);
            puntuacionesArray = getIntent().getDoubleArrayExtra("puntuacionesArray");
        }

        if (savedInstanceState == null)
        {
            new getLevelTask(this).execute();
        }

        else
        {
            isWordVisible = savedInstanceState.getBooleanArray(ISWORDVISIBLE);
            setViews();
        }
    }

    public void onSaveInstanceState (Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        GridLayout grid = findViewById(R.id.level8Grid);
        for (int i = 0; i < levelArray.length; i++)
        {
            View view = grid.getChildAt(i);
            if (view.getVisibility() == View.INVISIBLE)
            {
                isWordVisible[i] = false;
            }
        }
        savedInstanceState.putBooleanArray(ISWORDVISIBLE,isWordVisible);
    }

    private void setViews() {
        GridLayout grid = findViewById(R.id.level8Grid);
        for (int i = 0;i < levelArray.length;i++)
        {
            TextView textView = new TextView(this);
            textView.setText(levelArray[i].getPalabra());
            textView.setBackgroundResource(R.drawable.boxwords);
            textView.setGravity(Gravity.CENTER);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f),GridLayout.spec(GridLayout.UNDEFINED, 1f));
            params.setMargins(50,50,0,0);
            params.height = 100;
            params.width = 100;
            textView.setLayoutParams(params);
            textView.setOnLongClickListener(new myLongClickListener(textView,levelArray[i].getAcento()));
            if (!isWordVisible[i])
            {
                textView.setVisibility(View.INVISIBLE);
            }
            grid.addView(textView);
        }

        TextView cajaAgudas = findViewById(R.id.level8CajaAgudas);
        TextView cajaLlanas = findViewById(R.id.level8CajaLlanas);
        TextView cajaEsdrujulas = findViewById(R.id.level8CajaEsdrujulas);

        cajaAgudas.setOnDragListener(new myDragEventListener(1));
        cajaLlanas.setOnDragListener(new myDragEventListener(2));
        cajaEsdrujulas.setOnDragListener(new myDragEventListener(3));
    }

    public void checkWords(int acentuacion, int tipoCaja) {
        if (acentuacion == tipoCaja)
        {
            correctas++;
        }
        index++;
        if (index >= levelArray.length)
        {
            endLevel(8);
        }
    }

    protected void endLevel(int numNivel) {
        super.endLevel(numNivel);
        if (pin != null)
        {
            puntuacionesArray[5] = (float)(correctas*10)/(float)levelArray.length;
            new uploadResultsServerTask (this).execute();
        }
    }

    private void onVirtualClassEnd() {
        boolean isThereExercises = false;
        for (int i = 0; i < puntuacionesArray.length && isThereExercises == false; i++)
        {
            if (puntuacionesArray[i] != -1)
            {
                isThereExercises = true;
            }
        }
        if (isThereExercises)
        {
            new uploadResultsServerTask (this).execute();
        }
        else
        {
            Toast.makeText(this,"No se han encontrado ejercicios para ese pin determinado",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Level8Activity.this,VirtualClassActivity.class);
            startActivity(intent);
        }
    }

    private class getLevelTask extends ProgressTask<Nivel8[]>
    {

        public getLevelTask(Context context) {
            super(context);
        }

        @Override
        protected Nivel8[] work() throws Exception {
            return business.getNivel8(nickname,pin);
        }

        @Override
        protected void onFinish(Nivel8[] result) {
            levelArray = result;
            if (levelArray != null) {
                if (levelArray.length != 0) {
                    isWordVisible = new boolean[levelArray.length];
                    Arrays.fill(isWordVisible, true);
                    setViews();
                }
                else
                {
                    puntuacionesArray[5] = -1;
                    onVirtualClassEnd();
                }
            }
            else
            {
                Toast.makeText(Level8Activity.this, "No se ha podido obtener los ejercicios del servidor",Toast.LENGTH_LONG).show();
            }
        }
    }

    public class myLongClickListener implements View.OnLongClickListener {

        private View view;
        private int acentuacion;

        public myLongClickListener (View view, int acentuacion)
        {
            this.view = view;
            this.acentuacion = acentuacion;
        }

        public boolean onLongClick(View v) {
            ClipData.Item item = new ClipData.Item(String.valueOf(acentuacion));
            ClipDescription clipDescription = new ClipDescription(null, new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN });
            ClipData dragData = new ClipData(clipDescription,item);

            View.DragShadowBuilder myShadow = new MyDragShadowBuilder(view);

            v.startDrag(dragData,
                    myShadow,
                    v,
                    0
            );

            return true;
        }

        private class MyDragShadowBuilder extends View.DragShadowBuilder {
            private Drawable shadow;
            public MyDragShadowBuilder(View v) {
                super(v);
                shadow = new ColorDrawable(Color.LTGRAY);
            }

            @Override
            public void onProvideShadowMetrics (Point size, Point touch) {
                int width, height;
                width = getView().getWidth() / 2;
                height = getView().getHeight() / 2;
                shadow.setBounds(0, 0, width, height);
                size.set(width, height);
                touch.set(width / 2, height / 2);
            }

            @Override
            public void onDrawShadow(Canvas canvas) {
                shadow.draw(canvas);
            }
        }
    }

    protected class myDragEventListener implements View.OnDragListener {
        private int tipoCaja;

        public myDragEventListener(int tipoCaja)
        {
            this.tipoCaja = tipoCaja;
        }

        public boolean onDrag(View v, DragEvent event) {
            final int action = event.getAction();
            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        return true;
                    }
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:
                    return true;

                case DragEvent.ACTION_DROP:
                    ClipData.Item item = event.getClipData().getItemAt(0);
                    int acentuacion = Integer.valueOf(String.valueOf(item.getText()));
                    checkWords(acentuacion,tipoCaja);
                    View view = (View) event.getLocalState();
                    view.setVisibility(View.INVISIBLE);
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    return true;

                default:
                    Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                    break;
            }

            return false;
        }
    }

    private class uploadResultsServerTask extends ProgressTask <Boolean> {
        public uploadResultsServerTask(Context context) {
            super(context);
        }

        @Override
        protected Boolean work() throws Exception {
            return business.postResults(puntuacionesArray,pin,nickname);
        }

        @Override
        protected void onFinish(Boolean result) {
            if (result)
            {
                Toast.makeText(Level8Activity.this, "Se han subido las puntuaciones correctamente al servidor",Toast.LENGTH_SHORT).show();
            }

            else
            {
                Toast.makeText(Level8Activity.this, "No se han subido las puntuaciones correctamente al servidor",Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(Level8Activity.this,VirtualClassActivity.class);
            startActivity(intent);
        }
    }
}