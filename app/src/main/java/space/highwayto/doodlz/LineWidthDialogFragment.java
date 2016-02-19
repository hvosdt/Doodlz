package space.highwayto.doodlz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * Created by hvosdt on 18/02/16.
 */
public class LineWidthDialogFragment extends DialogFragment {

    private ImageView widthImageView;

    @Override
    public Dialog onCreateDialog(Bundle bundle){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View lineWidthDialogView = getActivity().getLayoutInflater().inflate(
                R.layout.fragment_line_width, null);
        builder.setView(lineWidthDialogView);

        builder.setTitle(R.string.title_line_width_dialog);
        builder.setCancelable(true);

        widthImageView = (ImageView) lineWidthDialogView.findViewById(R.id.widthImageView);

        final DoodleView doodleView = getDoodleFragment().getDoodleView();
        final SeekBar widthSeekBar = (SeekBar) lineWidthDialogView.findViewById(R.id.widthSeekBar);
        widthSeekBar.setOnSeekBarChangeListener(lineWidthChanged);
        widthSeekBar.setProgress(doodleView.getLineWidth());

        builder.setPositiveButton(R.string.title_line_width_dialog,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doodleView.setLineWidth(widthSeekBar.getProgress());
                    }
                });
        return builder.create();
    }

    private DoodleFragment getDoodleFragment(){
        return (DoodleFragment) getFragmentManager().findFragmentById(R.id.doodleFragment);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        DoodleFragment fragment = getDoodleFragment();

        if (fragment != null){
            fragment.setDialogOnScreen(true);
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        DoodleFragment fragment = getDoodleFragment();

        if(fragment != null){
            fragment.setDialogOnScreen(false);
        }
    }

    private SeekBar.OnSeekBarChangeListener lineWidthChanged =
            new SeekBar.OnSeekBarChangeListener() {

                Bitmap bitmap = Bitmap.createBitmap(400, 100, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    Paint p = new Paint();
                    p.setColor(getDoodleFragment().getDoodleView().getDrawingColor());
                    p.setStrokeCap(Paint.Cap.ROUND);
                    p.setStrokeWidth(progress);

                    bitmap.eraseColor(getResources().getColor(android.R.color.transparent));
                    canvas.drawLine(30, 50, 370, 50, p);
                    widthImageView.setImageBitmap(bitmap);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };

}
