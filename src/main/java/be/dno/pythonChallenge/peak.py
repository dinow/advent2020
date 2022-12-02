import pickle

banner_file = open("D:\\Development\\advent2020\\src\\main\\resources\\pythonChallenge\\banner.p", "rb")
banner = pickle.load(banner_file)
banner_file.close()
#print(banner)

for b in banner:
   for i in b:
      for x in range(i[1]):
         print (i[0], end="")
   print("")
#http://www.pythonchallenge.com/pc/def/channel.html