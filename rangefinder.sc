// <one line to give the program's name and a brief idea of what it does.>
//     Copyright (C) 2023  Wesley Johnson

//     This program is free software: you can redistribute it and/or modify
//     it under the terms of the GNU General Public License as published by
//     the Free Software Foundation, either version 3 of the License, or
//     (at your option) any later version.

//     This program is distributed in the hope that it will be useful,
//     but WITHOUT ANY WARRANTY; without even the implied warranty of
//     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//     GNU General Public License for more details.

//     You should have received a copy of the GNU General Public License
//     along with this program.  If not, see <https://www.gnu.org/licenses/>.

// x, y, z, dx, dy, dz
global_point1Tuple = [];
global_point2Tuple = [];

calculatePos() -> (
    //print('Calculation inputs: ' + global_point1Tuple + ', ' + global_point2Tuple);

    // The rangefinder works by finding the point where the two rays defined by the player's sightings intersect.
    // This can be solved one dimension at a time, since we have the look direction components already.
    // We first calculate the "time" until our "projectiles" will collide— these are imaginary projectiles that
    // move at 1m/s.  This is most accurate when the position delta used to calculate time of "impact" is greatest,
    // so first determine which axis has the largest delta, then use that.
    dx = global_point2Tuple:0 - global_point1Tuple:0;
    dy = global_point2Tuple:1 - global_point1Tuple:1;
    dz = global_point2Tuple:2 - global_point1Tuple:2;
    big = max(dx, dy, dz);
    if (big == dx,
        print('x');
        //  t = d/V
        t = (dx) / (global_point1Tuple:3 - global_point2Tuple:3),
    big == dy, // Else If
        print('y');
        t = (dy) / (global_point1Tuple:4 - global_point2Tuple:4),
    // Else
        print('z');
        t = (dz) / (global_point1Tuple:5 - global_point2Tuple:5)
    );
    
    // Now we can calculate the block position easily
    x = global_point1Tuple:0 + t * global_point1Tuple:3;
    y = global_point1Tuple:1 + t * global_point1Tuple:4;
    z = global_point1Tuple:2 + t * global_point1Tuple:5;
    return ([x, y, z])
);

__on_player_releases_item(player, item_tuple, hand)-> (
    if (item_tuple:0 == 'spyglass',
        posTuple = player ~ 'pos';
        lookTuple = player ~ 'look';
        if (item_tuple:2:'display':'Name' == '{"text":"Sight 1"}',
            // Point 1
            global_point1Tuple = [];
            put(global_point1Tuple, null, posTuple, 'extend');
            put(global_point1Tuple, null, lookTuple, 'extend');
            //print(global_point1Tuple),
        item_tuple:2:'display':'Name' == '{"text":"Sight 2"}', // Else If
            // Point 2
            global_point2Tuple = [];
            put(global_point2Tuple, null, posTuple, 'extend');
            put(global_point2Tuple, null, lookTuple, 'extend');
            //print(global_point2Tuple),
        );
        print(calculatePos())
    )
)
