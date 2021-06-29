package com.example.sandwichstand;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import java.util.ArrayList;


@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class MainActivityTest extends TestCase {

    private ActivityController<MainActivity> activityController;
    private  OrderImpl mockDataBase;

    @Before
    public void setup(){
        mockDataBase = Mockito.mock(OrderImpl.class);
        // when asking the `mockDataBase` to get the current items, return an empty list
        Mockito.when(mockDataBase.getCurrentSandwich())
                .thenReturn(null);

        activityController = Robolectric.buildActivity(MainActivity.class);

        // let the activity use our `mockDataBase` as the TodoItemsDataBase
        MainActivity activityUnderTest = activityController.get();
        activityUnderTest.dataBase = mockDataBase;
    }

    @Test
    public void when_activityIsLaunched_then_theEditTextStartsEmpty() {
        // setup
        activityController.create().visible();
        MainActivity activityUnderTest = activityController.get();
        EditText comment = activityUnderTest.findViewById(R.id.comment);
        Button save=activityUnderTest.findViewById(R.id.saveButton);
        String userInput = comment.getText().toString();

        // verify
        assertTrue(userInput.isEmpty());
//        assertFalse(save.isEnabled());


    }


    @Test
    public void when_dataBaseHasNoSandwich_then_MainActivityShowsNoSandwich() {
        // setup
        Sandwich sandwich=new Sandwich();
        Mockito.when(mockDataBase.getCurrentSandwich())
                .thenReturn(sandwich);

        // test - let the activity think it is being shown
        activityController.create().visible();

        // verify
        MainActivity activityUnderTest = activityController.get();
        ;
        assertNull(activityUnderTest.dataBase.getCurrentSandwich());
    }
    @Test
    public void when_noName_then_saveButtonUnAvailable() {
        // setup
        Sandwich sandwich=new Sandwich();
        Mockito.when(mockDataBase.getCurrentSandwich())
                .thenReturn(sandwich);

        // test - let the activity think it is being shown
        activityController.create().visible();
        MainActivity activityUnderTest = activityController.get();

        TextView name = activityUnderTest.findViewById(R.id.customerName);

        Button saveButton = activityUnderTest.findViewById(R.id.SaveButton);
        String nameInput = name.getText().toString();

        assertTrue(nameInput.isEmpty());
        assertFalse(saveButton.isEnabled());
    }

//    @Test
//    public void when_userPutInputAndClicksButton_then_activityShouldCallAddItem() {
//        // setup
//        String userInput = "Call my grandma today at 18:00";
//        activityController.create().visible(); // let the activity think it is being shown
//        MainActivity activityUnderTest = activityController.get();
//        EditText editText = activityUnderTest.findViewById(R.id.editTextInsertTask);
//        View fab = activityUnderTest.findViewById(R.id.buttonCreateTodoItem);
//
//        // test - mock user interactions
//        editText.setText(userInput);
//        fab.performClick();
//
//        // verify: verify that `mockDataBase.addNewInProgressItem()` was called, with exactly same string
//        Mockito.verify(mockDataBase).addNewInProgressItem(eq(userInput));
//    }
//
//    @Test
//    public void when_userPutInputAndClicksButton_then_inputShouldBeErasedFromEditText() {
//        String userInput = "Call my grandma today at 18:00";
//
//        activityController.create().visible(); // let the activity think it is being shown
//        MainActivity activityUnderTest = activityController.get();
//        EditText editText = activityUnderTest.findViewById(R.id.editTextInsertTask);
//        View fab = activityUnderTest.findViewById(R.id.buttonCreateTodoItem);
//
//        editText.setText(userInput);
//        fab.performClick();
//        assertEquals("", editText.getText().toString());
//
//        //    TODO: implement the test.
//        //     to set up the test, take a look at `when_userPutInputAndClicksButton_then_activityShouldCallAddItem()`
//        //     to verify, use methods such as "assertEquals(...)" or "assertTrue(...)"
//    }
//

////
//    @Test
//    public void when_dataBaseSays1ItemOfTypeInProgress_then_activityShouldShow1MatchingViewInRecyclerView(){
//        // setup
//
//        // when asking the `mockHolder` to get the current items, return a list with 1 item of type "in progress"
//        Sandwich sandwich = null;
//        Mockito.when(mockDataBase.getCurrentSandwich())
//                .thenReturn(sandwich);
////        TodoItem itemInProgress = new TodoItem();
//        // TODO: customize `itemInProgress` to have type IN-PROGRESS and description "do homework"
//        sandwich.g(itemInProgress);
//
//        // test - let the activity think it is being shown
//        activityController.create().visible();
//
//        // verify: make sure that the activity shows a matching subview in the recycler view
//        MainActivity activityUnderTest = activityController.get();
//        RecyclerView recyclerView = activityUnderTest.findViewById(R.id.recyclerTodoItemsList);
//
//        // 1. verify that adapter says there should be 1 item showing
//        RecyclerView.Adapter adapter = recyclerView.getAdapter();
//        assertNotNull(adapter);
//        assertEquals(1, adapter.getItemCount());
//
//        // 2. verify that the shown view has a checkbox being not-checked and has a TextView showing the correct description
//        View viewInRecycler = recyclerView.findViewHolderForAdapterPosition(0).itemView;
//        // TODO: implement.
//        //  use `viewInRecycler.findViewById(...)` to find the checkbox and the description subviews,
//        //  and make sure the checkbox is not checked and the TextView shows the correct description
//    }

//
//    @Test
//    public void when_dataBaseSays1ItemOfTypeDone_then_activityShouldShow1MatchingViewInRecyclerView(){
//        // setup
//
//        // when asking the `mockHolder` to get the current items, return a list with 1 item of type "DONE"
//        ArrayList<TodoItem> itemsReturnedByHolder = new ArrayList<>();
//        Mockito.when(mockDataBase.getCurrentItems())
//                .thenReturn(itemsReturnedByHolder);
//        TodoItem itemDone = new TodoItem();
//        // TODO: customize `itemDone` to have type DONE and description "buy tomatoes"
//        itemsReturnedByHolder.add(itemDone);
//
//        // test - let the activity think it is being shown
//        activityController.create().visible();
//
//        // verify: make sure that the activity shows a matching subview in the recycler view
//        MainActivity activityUnderTest = activityController.get();
//        RecyclerView recyclerView = activityUnderTest.findViewById(R.id.recyclerTodoItemsList);
//
//        // 1. verify that adapter says there should be 1 item showing
//        RecyclerView.Adapter adapter = recyclerView.getAdapter();
//        assertNotNull(adapter);
//        assertEquals(1, adapter.getItemCount());
//
//        // 2. verify that the shown view has a checkbox being checked and has a TextView showing the correct description
//        View viewInRecycler = recyclerView.findViewHolderForAdapterPosition(0).itemView;
//        // TODO: implement.
//        //  use `viewInRecycler.findViewById(...)` to find the checkbox and the description subviews,
//        //  and make sure the checkbox is checked and the TextView shows the correct description
//    }
}
