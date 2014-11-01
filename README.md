Breakthrough
============

The Breakthrough clone project as created by the Little Bandit team!


Adding levels
=============

The simplest way to add levels is to use Tiled Map Editor. The current level system supports only one tile but it is extensible for future needs. 

1. Once you have Tiled installed open up the baseLevel.tmx file and make your changes. 

2. Then save it as levelX.tmx (X being your level number. eg level3.tmx). 

3. Next, export to the Lua Files (.lua) extension and save the file as tiledOutput.lua. 

4. Go to the TiledConverter class in the gameutilities package and in the public static void main method change the name of the output file to levelX.map (Again, X being the level number you chose in the previous step. eg level3.map). 

This will generate a new .map file if you've followed the steps correctly. You may have to refresh your assets folder in case your IDE doesn't do this automatically for you.

You can now call the MapBuilder.buildLevelMap() method to generate your newly created level.


How the State system works
==========================

States represent game screens or states. For example, a main menu, highscore list, or a play state. States are added to the GameStateManager with the popAndPush() and pushNew() methods. The former removes the top most state and replaces it with new state. The latter adds the new state to the top of the list. The GameStateManager works with a Stack object. Please refer to Stack in the core Java documentation if you have any questions.

All new states are derived from the abstract State class. This implementation emulates the libGDX application setup with create(), render(), and dispose() methods. Additionally, there is an update() method where all game logic should go.

The State class includes a reference to a GameStateManager that handles the actual update() and render() calls. The State class has a final method called udateAndRender() which combines the update() and render() methods of it's child classes. This is the actual method that is called by the GameStateManager. This is done for simplicity and to avoid errors when a state is removed from the manager.


How the Entity system works
===========================

The Entity system has a few core packages. 

1. entites - Where the Entity interface and all derived Entity implementations should go. There is a SimpleEntity implementation that should fit many uses of the interface. This class can be extended with a constructor that calls SimpleEntity as super(). Any entity classes that need additional functionality should enxtend the SimpleEntity class, in most cases, and add the necessary functionality.

2. components - This includes two packages.
    a. rendercomponents - Where any render functionalities should be written. There is an interface called RenderComponent. This interface should be implemented and it's render() method used for render code.
    b. updatecomponents - This is where the entity logic goes. The interface UpdateComponent should be implemented and the code should be written in the update() method.
    
    As an example, say you have a Ball entity. You should create a BallUpdateComponent class that implements the UpdateComponent interface. Then, you would create additional UpdateComponent implementations to add the desired behavior. After, you call these components from the BallUpdateComponent class in the update() method. Similar for any special render components. For more examples look at the BallUpdateComponent class in the ballcomponents package.
    
3. enityutilities - This contains An EntityArrayList where all entities should be added when they are created. This EntityArrayList is then called in a game state with the updateAll() and renderAll() calls. This simplifies the process of having to call individual entities in a game state's render() and update() methods. The EntityArrayList works similar to an ArrayList. It is actually derived from the libGDX Array class which is a special implementation optomized for garbage reduction. This package also contains an EntityFactory used to create entities. If you have questions as to how a Factory works look up the Factory Method Design Pattern.
    
    As an example, when you are going to create a new entity use an EntityArrayList and call the add() method. Inside of the add() method call the EntityFactory and create your desired entity.

The Entity system contains one other class. The Postition class. This is a simple X and Y coordinate class. It's simple enough to use. Read through the code to understand it's simple getter and setter methods.
