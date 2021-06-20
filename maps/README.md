# Pacman Map Files

### Format
- The first line contains two integers, $N$ and $M$, representing the height and the width of the map.
- The next $N$ lines are the map contents. Each line has exactly $M$ characters, `.` represents a road grid, `#` represents a wall grid, and `?` represents a special grid.
- The next line contains a integer $K$, representing the number of highways
- The next $K$ lines are the highways. Each line has four integers $a_x$, $a_y$, $b_x$, $b_y$, representing there's a bidirectional highway connecting $(a_x, a_y)$ and $(b_x, b_y)$.
