package kr.co.kumoh.ce.dyobb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.*;
import com.facebook.model.*;
import android.content.Intent;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // start Facebook Login
        
        Session.openActiveSession(this,  true,  new Session.StatusCallback() {
        	
        	//callback when session changes state
        	
        	@Override
        	public void call (Session session, SessionState state, Exception exception) {
        		if(session.isOpened()) {
        			
        			//make request to the /me API
        			Request.newMeRequest(session, new Request.GraphUserCallback() {
        				
        				//callback after Graph API response with user object
        				@Override
        				public void onCompleted(GraphUser user, Response response) {
        					if(user !=null) {
        						TextView welcome = (TextView) findViewById(R.id.welcome);
        						welcome.setText("hello" + user.getName() + "!");
        					}
        				}
        			}).executeAsync();
        		}
        	}
        });
       
        
        

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
