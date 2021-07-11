package com.mosugu.sndrvadapter

interface SwipeAndDragAdapter  {

        fun onItemSwiped(position : Int)

        fun onItemMoved(fromPosition : Int, toPosition : Int)
    }

