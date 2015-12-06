// this comes from duncan
// http://stackoverflow.com/questions/1515437/java-function-for-arrays-like-phps-join
//http://stackoverflow.com/users/474189/duncan
// 
// My modifications :
// Overloading for ArrayList<String> 
// permutation the order of the arguments for being closer to the future String.join function to be available in Java8.
// change the name of the function combine->join and embed the whole in a class.

import java.io.*;
import java.lang.String;
import java.util.ArrayList;

class combine
{
    static String join(String glue,String[] s)
    {
      int k = s.length;
      if ( k == 0 )
      {
        return null;
      }
      StringBuilder out = new StringBuilder();
      out.append( s[0] );
      for ( int x=1; x < k; ++x )
      {
        out.append(glue).append(s[x]);
      }
      return out.toString();
    }

    static String join(String glue,ArrayList<String> s)
    {   
        String[] tmp=new String[s.size()];
        s.toArray(tmp);
        return join( glue,tmp  );
    }
}
