require "spec_helper"
require "game"

describe Game do
	describe "Show the use of roll and score methods" do
		let (:g1) { g1 = Game .new }
	    def roll_many_times(n, pins)
			n.times do
				g1.roll(pins)
	    	end
	    end
		it 'can roll a perfect game' do
			roll_many_times(12, 10)
			expect(g1.score).to eq(300)
		end
		it 'can roll a spare at last frame' do
			g2 = Game.new
		    #frame1
		    g2.roll(5)
		    g2.roll(5) #spare
		    #frame2
		    g2.roll(3)
		    g2.roll(4)
		    #frame3
		    g2.roll(10) #strike
		    #frame4
		    g2.roll(5)
		    g2.roll(3)
		    #frame5
		    g2.roll(7)
		    g2.roll(3) #spare
		    #frame6
		    g2.roll(10) #strike
		    #frame7
		    g2.roll(8)
		    g2.roll(2) #spare
		    #frame8
		    g2.roll(2)
		    g2.roll(2)
		    #frame9
		    g2.roll(4)
		    g2.roll(6) #spare
		    #frame10
		    g2.roll(8)
		    g2.roll(2) #spare
		    g2.roll(5) #row3
		    expect(g2.score).to eq(135)
	    end
	    it 'can roll a strike at last frame' do
			g3 = Game.new
		    #frame1
		    g3.roll(4)
		    g3.roll(1)
		    #frame2
		    g3.roll(3)
		    g3.roll(2)
		    #frame3
		    g3.roll(4)
		    g3.roll(6) #spare
		    #frame4
		    g3.roll(10) #strike
		    #frame5
		    g3.roll(5)
		    g3.roll(5) #spare
		    #frame6
		    g3.roll(2)
		    g3.roll(8) #spare
		    #frame7
		    g3.roll(2)
		    g3.roll(1)
		    #frame8
		    g3.roll(2)
		    g3.roll(8) #spare
		    #frame9
		    g3.roll(10)	#strike
		    #frame10
		    g3.roll(10) #strike
		    g3.roll(6) 
		    g3.roll(2) #row3
		    expect(g3.score).to eq(141)
	    end
	    it 'can roll no spare or strike at last frame (no row3)' do
	    	g4 = Game.new
			#frame1
			g4.roll(5)
			g4.roll(1) 
			#frame2
			g4.roll(3)
			g4.roll(4)
			#frame3
			g4.roll(10) #strike
			#frame4
			g4.roll(5)
			g4.roll(3)
			#frame5
			g4.roll(7)
			g4.roll(3) #spare
			#frame6
			g4.roll(10) #strike
			#frame7
			g4.roll(8)
			g4.roll(2) #spare
			#frame8
			g4.roll(2)
			g4.roll(2)
			#frame9
			g4.roll(4)
			g4.roll(6) #spare
			#frame10
			g4.roll(2)
			g4.roll(2)
			#no row3
			expect(g4.score).to eq(111)
		end
		it 'can roll a simple game (no spare or strike)' do
	    	g5 = Game.new
			#frame1
			g5.roll(1)
			g5.roll(1) 
			#frame2
			g5.roll(0)
			g5.roll(0)
			#frame3
			g5.roll(0)
			g5.roll(2)
			#frame4
			g5.roll(1)
			g5.roll(2)
			#frame5
			g5.roll(2)
			g5.roll(0) 
			#frame6
			g5.roll(1)
			g5.roll(0)
			#frame7
			g5.roll(1)
			g5.roll(3)
			#frame8
			g5.roll(2)
			g5.roll(2)
			#frame9
			g5.roll(1)
			g5.roll(4) 
			#frame10
			g5.roll(0)
			g5.roll(1)
			#no row3
			expect(g5.score).to eq(24)
		end
	end
			describe "Bowling Game Kata" do
		it 'can roll the given example' do
			g6 = Game.new
			#frame1
			g6.roll(1)
			g6.roll(4)
			#frame2
			g6.roll(4)
			g6.roll(5)
			#frame3
			g6.roll(6)
			g6.roll(4) #spare
			#frame4
			g6.roll(5)
			g6.roll(5) #spare
			#frame5
			g6.roll(10) #strike
			#frame6
			g6.roll(0)
			g6.roll(1)
			#frame7
			g6.roll(7)
			g6.roll(3) #spare
			#frame8
			g6.roll(6)
			g6.roll(4) #spare
			#frame9
			g6.roll(10) #strike
			#frame10
			g6.roll(2)
			g6.roll(8) #strike
			g6.roll(6) #row3
			expect(g6.score).to eq(133)
		end
	end
end
