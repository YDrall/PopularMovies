package me.drall.popularmovies.util;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class ViewUtils {

  public static List<View> getAllChildrenBFS(View v) {
      List<View> visited = new ArrayList<>();
      List<View> unvisited = new ArrayList<>();
      unvisited.add(v);

      while (!unvisited.isEmpty()) {
          View child = unvisited.remove(0);
          visited.add(child);
          if (!(child instanceof ViewGroup)) continue;
          ViewGroup group = (ViewGroup) child;
          final int childCount = group.getChildCount();
          for (int i=0; i<childCount; i++) unvisited.add(group.getChildAt(i));
      }

      return visited;
  }

}
