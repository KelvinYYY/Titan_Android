
package algorithm;

import java.io.IOException;
import java.util.*;

import com.sun.tools.javac.jvm.Items;
import db.MySQLConnection;
import entity.Item;
import external.YelpAPI;
import org.json.JSONArray;
import rpc.RpcHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Recommendation based on geo distance and similar categories.
public class GeoRecommendation {

    public List<Item> recommendItems(String userId, double lat, double lon) {
        MySQLConnection conn = new MySQLConnection();

        // step 1
        Set<String> list = conn.getFavoriteItemIds(userId);

        // step 2
        Set<String> cates = new HashSet<>();
        for (String item : list) {
            cates.addAll(conn.getCategories(item));
        }
        // step 3

        Set<Item> recommenditems = new HashSet<>();
        for (String cate : cates) {
            recommenditems.addAll(conn.searchItems(userId, lat, lon, cate));
        }
        // step 4
        List<Item> filteredItems = new ArrayList<>();
        for (Item item: recommenditems) {
            if (!list.contains(item.getItemId())) {
                filteredItems.add(item);
            }
        }
        // step 5. perform ranking of these items based on distance.
        Collections.sort(filteredItems, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                // Student question: can we make this ranking even better with
                // more dimensions?
                // What other feathers can be used here?
                double distance1 = getDistance(item1.getLatitude(), item1.getLongitude(), lat, lon);
                double distance2 = getDistance(item2.getLatitude(), item2.getLongitude(), lat, lon);
                // return the increasing order of distance.
                return Double.compare(distance1, distance2);
            }
        });

        return filteredItems;
    }

    // Calculate the distances between two geolocations.
    // Source : http://andrew.hedges.name/experiments/haversine/
    private static double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.sin(dlat / 2 / 180 * Math.PI) * Math.sin(dlat / 2 / 180 * Math.PI)
                + Math.cos(lat1 / 180 * Math.PI) * Math.cos(lat2 / 180 * Math.PI) * Math.sin(dlon / 2 / 180 * Math.PI)
                * Math.sin(dlon / 2 / 180 * Math.PI);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        // Radius of earth in miles.
        double R = 3961;
        return R * c;
    }
}




