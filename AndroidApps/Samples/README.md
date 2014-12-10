This is an Android demo application for Simple ToDo list.
Time spent: 40 hours spent in total

Completed user stories:

Required: User can add items to do list.

Required: User can delete items from to do list

Required: User can edit to do items.

Required: Items are saved and later retrieved.

Optional:

        Mark items as completed by clicking checkbox
        
        Save items to SQLite database
        
        Styling items and backgroud
        
        Dialog fragment for editign items (Commented out the code path and added inline editing)

Work in Progress:

        Support for completion due dates for items
        
        Priority for items


Notes:

	1. Editing of items
		Version 1: Edit item activity was added so that user is taken to a new screen to edit item. Provided Save button to save items and cancel to cancel editing.Call commented out now
		Version 2: Edit item dialog was added for user to edit the item. Provided save button to save items and cancel to cancel editing. Call commented out now. 
		Version 3: Edit item is done inline.Item is saved when user moves to new item or tabs out.
	
	2. Saving items
		Version 1: Save to do list to "todo.txt". Code commented out
		Version 2: Save to do list to SQLite database.

Additional tried differrent styling, selcetors, color changes etc. 

![Alt text](https://github.com/prafulmantale/MobileDevelopment/blob/master/AndroidApps/ToDoList.gif)
