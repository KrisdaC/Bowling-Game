class Game
	def initialize
		@rolls = []
    end

	def roll(pins)
		@rolls << pins
 	end
 	
 	def score
		score = 0
		row1 = 0
		frame = 0
		while frame < 10
			if strike(row1)
				score += 10 + strike_bonus(row1)
            	row1 +=1
        	elsif spare(row1)
				score += 10 + spare_bonus(row1)
				row1 += 2
			else
				score += @rolls[row1] + @rolls[row1+1]
				row1 += 2
			end
			frame +=1
     	end
     	score
   end
 
   def spare(row1)
		@rolls[row1] + @rolls[row1+1] == 10
   end
   def spare_bonus(row1)
         @rolls[row1 + 2]
   end

   def strike(row1)
		@rolls[row1] == 10
   end
   def strike_bonus(row1)
         @rolls[row1+1] + @rolls[row1+2]
   end
end