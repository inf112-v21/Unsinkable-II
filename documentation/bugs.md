To keep control on bugs. Take note in this document. 

##Robot does not respawn after death
- Bug registered 29.03.21
- Bug tested manually
  Exception in thread "Game Thread" java.lang.NullPointerException
  at java.base/java.util.Objects.requireNonNull(Objects.java:208)
  at RoboRally.Game.Engine.RoboRallyGame.lambda$getRobotTurnOrder$1(RoboRallyGame.java:106)
  at java.base/java.util.Comparator.lambda$comparing$ea9a8b3a$1(Comparator.java:436)
  at java.base/java.util.PriorityQueue.siftUpUsingComparator(PriorityQueue.java:660)
  at java.base/java.util.PriorityQueue.siftUp(PriorityQueue.java:637)
  at java.base/java.util.PriorityQueue.offer(PriorityQueue.java:330)
  at java.base/java.util.PriorityQueue.add(PriorityQueue.java:311)
  at java.base/java.util.AbstractQueue.addAll(AbstractQueue.java:187)
  at RoboRally.Game.Engine.RoboRallyGame.getRobotTurnOrder(RoboRallyGame.java:107)
  at RoboRally.Game.Engine.GameLoop.turn(GameLoop.java:66)
  at RoboRally.Game.Engine.GameLoop.round(GameLoop.java:52)
  at RoboRally.Game.Engine.GameLoop.run(GameLoop.java:41)
  at java.base/java.lang.Thread.run(Thread.java:832)