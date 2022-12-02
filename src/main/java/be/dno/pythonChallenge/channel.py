


def get_next_nothing(cur_n):
   current_file = open("D:\\Development\\advent2020\\src\\main\\resources\\pythonChallenge\\channel\\"+cur_n+".txt", "r")
   lines = current_file.read()
   current_file.close()
   print(lines)
   index=lines.index('Next nothing is ')
   next_nothing=lines[index+16:]
   return next_nothing

current_nothing = "90052"
while current_nothing != "":
   current_nothing = get_next_nothing(current_nothing)
