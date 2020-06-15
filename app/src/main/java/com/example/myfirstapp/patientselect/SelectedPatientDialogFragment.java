package com.example.myfirstapp.patientselect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.example.myfirstapp.HomeScreen;
import com.example.myfirstapp.MainActivity;
import com.example.myfirstapp.patientselect.dummy.DummyContent;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myfirstapp.R;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     SelectedPatientDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class SelectedPatientDialogFragment extends BottomSheetDialogFragment {

    // change name, picture of xml ... either reference database through position
    // or send directly from fragment
    private TextView nameText;
    private ImageView profileImage;
    private Button confirmButton;
    private Map<String, DummyContent.PatientItem> dummyData = DummyContent.ITEM_MAP;

    private static final String PATIENT_ID = "patient_id"; //

    public static SelectedPatientDialogFragment newInstance(String patientId) {
        final SelectedPatientDialogFragment fragment = new SelectedPatientDialogFragment();
        final Bundle args = new Bundle();
        // arguments passed on from activity
        args.putString(PATIENT_ID, patientId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet_patient_selected, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // always use findViewById in onViewCreated, as view has been created by this point as
        // compared to onCreateView
        nameText = view.findViewById(R.id.selected_patient_name);
        profileImage = view.findViewById(R.id.selected_patient_pfp);
        confirmButton = view.findViewById(R.id.selected_patient_confirm);

        String patientId = getArguments().getString(PATIENT_ID);
        DummyContent.PatientItem patient = dummyData.get(patientId);
        // also set image view here !
        if(nameText != null) nameText.setText(patient.name);

        // adding onclick listener to confirm button
        confirmButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent toPatientMain = new Intent(getActivity(), HomeScreen.class);
                    startActivity(toPatientMain);
            }
        });
    }

    /*

    public void setFragmentData(String patientId) {
        DummyContent.PatientItem patient = dummyData.get(patientId);
        // also set image view here !
        if(nameText != null) nameText.setText(patient.name);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new SelectedPatientAdapter(getArguments().getInt(ARG_ITEM_COUNT)));
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        final TextView text;

        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            // TODO: Customize the item layout
            super(inflater.inflate(R.layout.selected_patient_item, parent, false));
            text = itemView.findViewById(R.id.text);
        }
    }

    private class SelectedPatientAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final int mItemCount;

        SelectedPatientAdapter(int itemCount) {
            mItemCount = itemCount;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.text.setText(String.valueOf(position));
        }

        @Override
        public int getItemCount() {
            return mItemCount;
        }

    }
    */

}
