package space.highwayto.doodlz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by hvosdt on 18/02/16.
 */
public class EraseImageDialogFragment extends DialogFragment {

    public Dialog onCreateDialog(Bundle bundle){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.message_erase);
        builder.setCancelable(true);

        builder.setPositiveButton(R.string.button_erase,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getDoodleFragment().getDoodleView().clear();
                    }
                });
        builder.setNegativeButton(R.string.button_cansel, null);

        return builder.create();
    }

    private DoodleFragment getDoodleFragment() {
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

        if (fragment != null){
            fragment.setDialogOnScreen(false);
        }
    }

}
