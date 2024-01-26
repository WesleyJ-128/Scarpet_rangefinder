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

__on_player_releases_item(player, item_tuple, hand)-> (
    if (item_tuple:0 == 'spyglass',
        posTuple = player ~ 'pos';
        lookTuple = player ~ 'look';
        if (item_tuple:2:'display':'Name' == '{"text":"Sight 1"}',
            // Point 1
            global_point1Tuple = [];
            put(global_point1Tuple, null, posTuple, 'extend');
            put(global_point1Tuple, null, lookTuple, 'extend');
            print(global_point1Tuple),
        item_tuple:2:'display':'Name' == '{"text":"Sight 2"}', // Else If
            // Point 2
            global_point1Tuple = [];
            put(global_point2Tuple, null, posTuple, 'extend');
            put(global_point2Tuple, null, lookTuple, 'extend');
            print(global_point2Tuple),
        )
    )
)
