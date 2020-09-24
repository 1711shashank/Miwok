package android.example.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words,int colorResourceId) {

        super(context, 0, words);
        mColorResourceId= colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from( getContext() ).inflate(
                    R.layout.list_item, parent, false );
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentWord = getItem( position );

        TextView miwokTextView = (TextView) listItemView.findViewById( R.id.miwok_text_view );
        miwokTextView.setText( currentWord.getMiwokTranslation() );


        TextView defultTextView = (TextView) listItemView.findViewById( R.id.default_text_view );

        defultTextView.setText( currentWord.getDefaultTranslation());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        if(currentWord.hasImage())
            imageView.setImageResource(currentWord.getImageResourceId());
        else
            imageView.setVisibility( View.GONE );

        View textContainer = listItemView.findViewById( R.id.text_container );
        int color = ContextCompat.getColor( getContext(), mColorResourceId );
        textContainer.setBackgroundColor( color );
        return listItemView;
    }
}
