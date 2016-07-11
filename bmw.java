import nrc.fuzzy.*;

import java.io.*;
public class bmw
{
	public bmw()
	{
		
	}
	public static void main(String[] argv) throws FuzzyException,IOException
	  {
		RightLinearFunction rlf = new RightLinearFunction();
		LeftLinearFunction llf = new LeftLinearFunction();
		FuzzyValue glight=null,gmusic=null,gmassage=null,gctemp=null,gperfume=null;
		FuzzyValueVector fvv = null;
		FuzzyValueVector fvv2 = null;
		
     // Mood has terms OK, Good and Great
		FuzzyVariable music = new FuzzyVariable("Music",1.0,10.0, "On a scale 1-10 calm to rock music");
		music.addTerm("calm", new LFuzzySet(1.0,4.0,llf));
	    music.addTerm("medium", new TriangleFuzzySet(4.0,6.0,7.0));
	    music.addTerm("rock", new RFuzzySet(7.0, 10.0,rlf));
	 // Car Perfume has terms Pleasant, Refreshing
	 	FuzzyVariable perfume = new FuzzyVariable("Perfume",1.0,3.0, "On a scale 1-3 Pleasant to Refreshing Odour");
	 	perfume.addTerm("pleasant", new TriangleFuzzySet(1.0,2.0,2.5));
	 	perfume.addTerm("refreshing", new RFuzzySet(2.0,3.0,rlf));   
	 // Lighting has terms dim, moderate and Bright
	    FuzzyVariable light = new FuzzyVariable("Light", 1.0, 10.0, "Brightness on a scale 1-10");
	    light.addTerm("dim", new LFuzzySet(1.0,4.5,llf));
	    light.addTerm("moderate", new TriangleFuzzySet(4.5,5.0,6.0));
	    light.addTerm("bright", new TriangleFuzzySet(6.0,9.0,10.0));
	 // Massage has parameters Low Medium and High
	    FuzzyVariable massage = new FuzzyVariable("Massage", 1.0, 12.0, "number of rollers");
	    massage.addTerm("low", new LFuzzySet(1.0, 5.0,llf));
	    massage.addTerm("medium", new TriangleFuzzySet(5.0,7.0,9.0));
	    massage.addTerm("high", new TrapezoidFuzzySet(8.0,9.0,10.0,12.0));
	 // Temperature User Parameters cold, medium, and hot
	    FuzzyVariable temp = new FuzzyVariable("Temperature", 0.0,50.0, "C");
	    temp.addTerm("cold", new LFuzzySet(0.0, 15.0,llf));
	    temp.addTerm("medium", new TriangleFuzzySet(16.0,25.0,36.0));
	    temp.addTerm("hot", new TrapezoidFuzzySet(37.0,42.0,45.0,50.0));
	 // Temperature Car Parameters cold, medium, and hot
	    FuzzyVariable ctemp = new FuzzyVariable("Car Temperature", 10.0,40.0, "C");
	    ctemp.addTerm("cold", new LFuzzySet(10.0, 16.0,llf));
	    ctemp.addTerm("medium", new TriangleFuzzySet(16.0,25.0,36.0));
	    ctemp.addTerm("hot", new TrapezoidFuzzySet(30.0,35.0,38.0,40.0));
	 // Car Atmosphere Parameters Sleep,Relax,Work
	    FuzzyVariable atm = new FuzzyVariable("Atmosphere", 0.0,10.0, "0-10 Scale of Sleep,Relax and Work");
	    atm.addTerm("sleep", new LFuzzySet(0.0, 3.0,llf));
	    atm.addTerm("relax", new TriangleFuzzySet(3.0,4.5,6.0));
	    atm.addTerm("work", new TrapezoidFuzzySet(6.0,6.8,8.0,10.0));
     //	Rules
	 // Rule-1
	    FuzzyRule sleepyc = new FuzzyRule();
	    sleepyc.addAntecedent(new FuzzyValue(atm,"sleep"));
	    sleepyc.addAntecedent(new FuzzyValue(temp,"cold"));
	    sleepyc.addConclusion(new FuzzyValue(light,"dim"));
	    sleepyc.addConclusion(new FuzzyValue(music,"calm"));
	    sleepyc.addConclusion(new FuzzyValue(massage,"low"));
	    sleepyc.addConclusion(new FuzzyValue(ctemp,"medium"));
	 // Rule-2
	    FuzzyRule relaxyc = new FuzzyRule();
	    relaxyc.addAntecedent(new FuzzyValue(atm,"relax"));
	    relaxyc.addAntecedent(new FuzzyValue(temp,"cold"));
	    relaxyc.addConclusion(new FuzzyValue(light,"moderate"));
	    relaxyc.addConclusion(new FuzzyValue(music,"medium"));
	    relaxyc.addConclusion(new FuzzyValue(massage,"high"));
	    relaxyc.addConclusion(new FuzzyValue(ctemp,"medium"));
	 // Rule-3
	    FuzzyRule workyc = new FuzzyRule();
	    workyc.addAntecedent(new FuzzyValue(atm,"work"));
	    workyc.addAntecedent(new FuzzyValue(temp,"cold"));
	    workyc.addConclusion(new FuzzyValue(light,"bright"));
	    workyc.addConclusion(new FuzzyValue(music,"calm"));
	    workyc.addConclusion(new FuzzyValue(massage,"medium"));
	    workyc.addConclusion(new FuzzyValue(ctemp,"medium"));
	 // Rule-4
	    FuzzyRule sleepym = new FuzzyRule();
	    sleepym.addAntecedent(new FuzzyValue(atm,"sleep"));
	    sleepym.addAntecedent(new FuzzyValue(temp,"medium"));
	    sleepym.addConclusion(new FuzzyValue(light,"dim"));
	    sleepym.addConclusion(new FuzzyValue(music,"calm"));
	    sleepym.addConclusion(new FuzzyValue(massage,"low"));
	    sleepym.addConclusion(new FuzzyValue(ctemp,"medium"));
	 // Rule-5
	    FuzzyRule relaxym = new FuzzyRule();
	    relaxym.addAntecedent(new FuzzyValue(atm,"relax"));
	    relaxym.addAntecedent(new FuzzyValue(temp,"medium"));
	    relaxym.addConclusion(new FuzzyValue(light,"moderate"));
	    relaxym.addConclusion(new FuzzyValue(music,"rock"));
	    relaxym.addConclusion(new FuzzyValue(massage,"high"));
	    relaxym.addConclusion(new FuzzyValue(ctemp,"medium"));
	 // Rule-6
	    FuzzyRule workym = new FuzzyRule();
	    workym.addAntecedent(new FuzzyValue(atm,"work"));
	    workym.addAntecedent(new FuzzyValue(temp,"medium"));
	    workym.addConclusion(new FuzzyValue(light,"bright"));
	    workym.addConclusion(new FuzzyValue(music,"calm"));
	    workym.addConclusion(new FuzzyValue(massage,"medium"));
	    workym.addConclusion(new FuzzyValue(ctemp,"medium"));
	 // Rule-7
	    FuzzyRule sleepyh = new FuzzyRule();
	    sleepyh.addAntecedent(new FuzzyValue(atm,"sleep"));
	    sleepyh.addAntecedent(new FuzzyValue(temp,"hot"));
	    sleepyh.addConclusion(new FuzzyValue(light,"dim"));
	    sleepyh.addConclusion(new FuzzyValue(music,"calm"));
	    sleepyh.addConclusion(new FuzzyValue(massage,"low"));
	    sleepyh.addConclusion(new FuzzyValue(ctemp,"medium"));
	 // Rule-8
	    FuzzyRule relaxyh = new FuzzyRule();
	    relaxyh.addAntecedent(new FuzzyValue(atm,"relax"));
	    relaxyh.addAntecedent(new FuzzyValue(temp,"hot"));
	    relaxyh.addConclusion(new FuzzyValue(light,"moderate"));
	    relaxyh.addConclusion(new FuzzyValue(music,"medium"));
	    relaxyh.addConclusion(new FuzzyValue(massage,"high"));
	    relaxyh.addConclusion(new FuzzyValue(ctemp,"cold"));
	 // Rule-9
	    FuzzyRule workyh = new FuzzyRule();
	    workyh.addAntecedent(new FuzzyValue(atm,"work"));
	    workyh.addAntecedent(new FuzzyValue(temp,"hot"));
	    workyh.addConclusion(new FuzzyValue(light,"bright"));
	    workyh.addConclusion(new FuzzyValue(music,"calm"));
	    workyh.addConclusion(new FuzzyValue(massage,"medium"));
	    workyh.addConclusion(new FuzzyValue(ctemp,"cold"));
	 // Rule-10
	    FuzzyRule perfumeyh = new FuzzyRule();
	    perfumeyh.addAntecedent(new FuzzyValue(light,"bright"));
	    perfumeyh.addAntecedent(new FuzzyValue(music,"calm"));
	    perfumeyh.addAntecedent(new FuzzyValue(massage,"medium"));
	    perfumeyh.addAntecedent(new FuzzyValue(ctemp,"cold"));
	    perfumeyh.addConclusion(new FuzzyValue(perfume,"pleasant"));
	 // Rule-11
	    FuzzyRule perfumeym = new FuzzyRule();
	    perfumeym.addAntecedent(new FuzzyValue(light,"bright"));
	    perfumeym.addAntecedent(new FuzzyValue(music,"rock"));
	    perfumeym.addAntecedent(new FuzzyValue(massage,"medium"));
	    perfumeym.addAntecedent(new FuzzyValue(ctemp,"medium"));
	    perfumeym.addConclusion(new FuzzyValue(perfume,"refreshing"));  
	 // Input Variables
	    double t,a;
	    BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Enter how you want your car atmosphere on a scale 1-10 1-sleepy 10-work");
	    a=Double.parseDouble(br.readLine());
	    System.out.println("Enter the temperature outside in C");
	    t=Double.parseDouble(br.readLine());
	    FuzzyValue inputatm =  new FuzzyValue(atm, new TriangleFuzzySet(a-0.05, 
                a, a+0.05));
        FuzzyValue inputtemp =  new FuzzyValue(temp, new TriangleFuzzySet(t-0.05, 
                t, t+0.05));
     // fire rules
        
        sleepyc.removeAllInputs();
	    sleepyc.addInput(inputatm);
	    sleepyc.addInput(inputtemp);
	    sleepym.removeAllInputs();
	    sleepym.addInput(inputatm);
	    sleepym.addInput(inputtemp);
	    sleepyh.removeAllInputs();
	    sleepyh.addInput(inputatm);
	    sleepyh.addInput(inputtemp);
	    
	    relaxyc.removeAllInputs();
	    relaxyc.addInput(inputatm);
	    relaxyc.addInput(inputtemp);
	    relaxym.removeAllInputs();
	    relaxym.addInput(inputatm);
	    relaxym.addInput(inputtemp);
	    relaxyh.removeAllInputs();
	    relaxyh.addInput(inputatm);
	    relaxyh.addInput(inputtemp);
	    
	    workyc.removeAllInputs();
	    workyc.addInput(inputatm);
	    workyc.addInput(inputtemp);
	    workym.removeAllInputs();
	    workym.addInput(inputatm);
	    workym.addInput(inputtemp);
	    workyh.removeAllInputs();
	    workyh.addInput(inputatm);
	    workyh.addInput(inputtemp);
	    
	   // Conditions and fuzzy Unions
	    if(sleepyc.testRuleMatching())
	    { 
	    	fvv=sleepyc.execute();
	    	if (glight == null)
	    		 glight = fvv.fuzzyValueAt(0);
	    	else
	    		 glight = glight.fuzzyUnion(fvv.fuzzyValueAt(0));
	    	if (gmusic == null)
	    		 gmusic = fvv.fuzzyValueAt(1);
	    	else
	    		 gmusic = gmusic.fuzzyUnion(fvv.fuzzyValueAt(1));
	    	if (gmassage == null)
	    		 gmassage = fvv.fuzzyValueAt(2);
	    	else
	    		 gmassage = gmassage.fuzzyUnion(fvv.fuzzyValueAt(2));
	        if (gctemp == null)
   		         gctemp = fvv.fuzzyValueAt(3);
   	        else
   		         gctemp = gctemp.fuzzyUnion(fvv.fuzzyValueAt(3));
	        
	        perfumeyh.removeAllInputs();
			perfumeym.removeAllInputs(); 
			perfumeyh.addInput(glight);
			perfumeyh.addInput(gmusic);
			perfumeyh.addInput(gmassage);
			perfumeyh.addInput(gctemp);
			perfumeym.addInput(glight);
			perfumeym.addInput(gmusic);
			perfumeym.addInput(gmassage);
			perfumeym.addInput(gctemp);
		    if(perfumeyh.testRuleMatching())
		    {
		        fvv2 = perfumeyh.execute();  
		    	if (gperfume == null)
		    		 gperfume = fvv2.fuzzyValueAt(0);
		    	else
		    		 gperfume = gperfume.fuzzyUnion(fvv2.fuzzyValueAt(0));
		     }
		     if(perfumeym.testRuleMatching())
		     {
		    	fvv2 = perfumeym.execute();  
		    	if (gperfume == null)
		    		 gperfume = fvv2.fuzzyValueAt(0);
		    	else
		    		 gperfume = gperfume.fuzzyUnion(fvv2.fuzzyValueAt(0));
	         }
	    }    
	    if(sleepym.testRuleMatching())
	    { 
	    	fvv=sleepym.execute();
	    	if (glight == null)
	    		 glight = fvv.fuzzyValueAt(0);
	    	else
	    		 glight = glight.fuzzyUnion(fvv.fuzzyValueAt(0));
	    	if (gmusic == null)
	    		 gmusic = fvv.fuzzyValueAt(1);
	    	else
	    		 gmusic = gmusic.fuzzyUnion(fvv.fuzzyValueAt(1));
	    	if (gmassage == null)
	    		 gmassage = fvv.fuzzyValueAt(2);
	    	else
	    		 gmassage = gmassage.fuzzyUnion(fvv.fuzzyValueAt(2));
	        if (gctemp == null)
   		         gctemp = fvv.fuzzyValueAt(3);
   	        else
   		         gctemp = gctemp.fuzzyUnion(fvv.fuzzyValueAt(3));
	        
	        perfumeyh.removeAllInputs();
			perfumeym.removeAllInputs(); 
			perfumeyh.addInput(glight);
			perfumeyh.addInput(gmusic);
			perfumeyh.addInput(gmassage);
			perfumeyh.addInput(gctemp);
			perfumeym.addInput(glight);
			perfumeym.addInput(gmusic);
			perfumeym.addInput(gmassage);
			perfumeym.addInput(gctemp);
		    if(perfumeyh.testRuleMatching())
		    {
		        fvv2 = perfumeyh.execute();  
		    	if (gperfume == null)
		    		 gperfume = fvv2.fuzzyValueAt(0);
		    	else
		    		 gperfume = gperfume.fuzzyUnion(fvv2.fuzzyValueAt(0));
		     }
		     if(perfumeym.testRuleMatching())
		     {
		    	fvv2 = perfumeym.execute();  
		    	if (gperfume == null)
		    		 gperfume = fvv2.fuzzyValueAt(0);
		    	else
		    		 gperfume = gperfume.fuzzyUnion(fvv2.fuzzyValueAt(0));
	         }
	    }
	    if(sleepyh.testRuleMatching())
	    { 
	    	fvv=sleepyh.execute();
	    	if (glight == null)
	    		 glight = fvv.fuzzyValueAt(0);
	    	else
	    		 glight = glight.fuzzyUnion(fvv.fuzzyValueAt(0));
	    	if (gmusic == null)
	    		 gmusic = fvv.fuzzyValueAt(1);
	    	else
	    		 gmusic = gmusic.fuzzyUnion(fvv.fuzzyValueAt(1));
	    	if (gmassage == null)
	    		 gmassage = fvv.fuzzyValueAt(2);
	    	else
	    		 gmassage = gmassage.fuzzyUnion(fvv.fuzzyValueAt(2));
	        if (gctemp == null)
   		         gctemp = fvv.fuzzyValueAt(3);
   	        else
   		         gctemp = gctemp.fuzzyUnion(fvv.fuzzyValueAt(3));
	        
	        perfumeyh.removeAllInputs();
			perfumeym.removeAllInputs(); 
			perfumeyh.addInput(glight);
			perfumeyh.addInput(gmusic);
			perfumeyh.addInput(gmassage);
			perfumeyh.addInput(gctemp);
			perfumeym.addInput(glight);
			perfumeym.addInput(gmusic);
			perfumeym.addInput(gmassage);
			perfumeym.addInput(gctemp);
		    if(perfumeyh.testRuleMatching())
		    {
		        fvv2 = perfumeyh.execute();  
		    	if (gperfume == null)
		    		 gperfume = fvv2.fuzzyValueAt(0);
		    	else
		    		 gperfume = gperfume.fuzzyUnion(fvv2.fuzzyValueAt(0));
		     }
		     if(perfumeym.testRuleMatching())
		     {
		    	fvv2 = perfumeym.execute();  
		    	if (gperfume == null)
		    		 gperfume = fvv2.fuzzyValueAt(0);
		    	else
		    		 gperfume = gperfume.fuzzyUnion(fvv2.fuzzyValueAt(0));
	         }
	    }
	    if(relaxyc.testRuleMatching())
	    { 
	    	fvv=relaxyc.execute();
	    	if (glight == null)
	    		 glight = fvv.fuzzyValueAt(0);
	    	else
	    		 glight = glight.fuzzyUnion(fvv.fuzzyValueAt(0));
	    	if (gmusic == null)
	    		 gmusic = fvv.fuzzyValueAt(1);
	    	else
	    		 gmusic = gmusic.fuzzyUnion(fvv.fuzzyValueAt(1));
	    	if (gmassage == null)
	    		 gmassage = fvv.fuzzyValueAt(2);
	    	else
	    		 gmassage = gmassage.fuzzyUnion(fvv.fuzzyValueAt(2));
	        if (gctemp == null)
   		         gctemp = fvv.fuzzyValueAt(3);
   	        else
   		         gctemp = gctemp.fuzzyUnion(fvv.fuzzyValueAt(3));
	        
	        perfumeyh.removeAllInputs();
			perfumeym.removeAllInputs(); 
			perfumeyh.addInput(glight);
			perfumeyh.addInput(gmusic);
			perfumeyh.addInput(gmassage);
			perfumeyh.addInput(gctemp);
			perfumeym.addInput(glight);
			perfumeym.addInput(gmusic);
			perfumeym.addInput(gmassage);
			perfumeym.addInput(gctemp);
		    if(perfumeyh.testRuleMatching())
		    {
		        fvv2 = perfumeyh.execute();  
		    	if (gperfume == null)
		    		 gperfume = fvv2.fuzzyValueAt(0);
		    	else
		    		 gperfume = gperfume.fuzzyUnion(fvv2.fuzzyValueAt(0));
		     }
		     if(perfumeym.testRuleMatching())
		     {
		    	fvv2 = perfumeym.execute();  
		    	if (gperfume == null)
		    		 gperfume = fvv2.fuzzyValueAt(0);
		    	else
		    		 gperfume = gperfume.fuzzyUnion(fvv2.fuzzyValueAt(0));
	         }
	    }
	    if(relaxym.testRuleMatching())
	    { 
	    	fvv=relaxym.execute();
	    	if (glight == null)
	    		 glight = fvv.fuzzyValueAt(0);
	    	else
	    		 glight = glight.fuzzyUnion(fvv.fuzzyValueAt(0));
	    	if (gmusic == null)
	    		 gmusic = fvv.fuzzyValueAt(1);
	    	else
	    		 gmusic = gmusic.fuzzyUnion(fvv.fuzzyValueAt(1));
	    	if (gmassage == null)
	    		 gmassage = fvv.fuzzyValueAt(2);
	    	else
	    		 gmassage = gmassage.fuzzyUnion(fvv.fuzzyValueAt(2));
	        if (gctemp == null)
   		         gctemp = fvv.fuzzyValueAt(3);
   	        else
   		         gctemp = gctemp.fuzzyUnion(fvv.fuzzyValueAt(3));
	        
	        perfumeyh.removeAllInputs();
			perfumeym.removeAllInputs(); 
			perfumeyh.addInput(glight);
			perfumeyh.addInput(gmusic);
			perfumeyh.addInput(gmassage);
			perfumeyh.addInput(gctemp);
			perfumeym.addInput(glight);
			perfumeym.addInput(gmusic);
			perfumeym.addInput(gmassage);
			perfumeym.addInput(gctemp);
		    if(perfumeyh.testRuleMatching())
		    {
		        fvv2 = perfumeyh.execute();  
		    	if (gperfume == null)
		    		 gperfume = fvv2.fuzzyValueAt(0);
		    	else
		    		 gperfume = gperfume.fuzzyUnion(fvv2.fuzzyValueAt(0));
		     }
		     if(perfumeym.testRuleMatching())
		     {
		    	fvv2 = perfumeym.execute();  
		    	if (gperfume == null)
		    		 gperfume = fvv2.fuzzyValueAt(0);
		    	else
		    		 gperfume = gperfume.fuzzyUnion(fvv2.fuzzyValueAt(0));
	         }
	    }
	    if(relaxyh.testRuleMatching())
	    { 
	    	fvv=relaxyh.execute();
	    	if (glight == null)
	    		 glight = fvv.fuzzyValueAt(0);
	    	else
	    		 glight = glight.fuzzyUnion(fvv.fuzzyValueAt(0));
	    	if (gmusic == null)
	    		 gmusic = fvv.fuzzyValueAt(1);
	    	else
	    		 gmusic = gmusic.fuzzyUnion(fvv.fuzzyValueAt(1));
	    	if (gmassage == null)
	    		 gmassage = fvv.fuzzyValueAt(2);
	    	else
	    		 gmassage = gmassage.fuzzyUnion(fvv.fuzzyValueAt(2));
	        if (gctemp == null)
   		         gctemp = fvv.fuzzyValueAt(3);
   	        else
   		         gctemp = gctemp.fuzzyUnion(fvv.fuzzyValueAt(3));
	        
	        perfumeyh.removeAllInputs();
			perfumeym.removeAllInputs(); 
			perfumeyh.addInput(glight);
			perfumeyh.addInput(gmusic);
			perfumeyh.addInput(gmassage);
			perfumeyh.addInput(gctemp);
			perfumeym.addInput(glight);
			perfumeym.addInput(gmusic);
			perfumeym.addInput(gmassage);
			perfumeym.addInput(gctemp);
		    if(perfumeyh.testRuleMatching())
		    {
		        fvv2 = perfumeyh.execute();  
		    	if (gperfume == null)
		    		 gperfume = fvv2.fuzzyValueAt(0);
		    	else
		    		 gperfume = gperfume.fuzzyUnion(fvv2.fuzzyValueAt(0));
		     }
		     if(perfumeym.testRuleMatching())
		     {
		    	fvv2 = perfumeym.execute();  
		    	if (gperfume == null)
		    		 gperfume = fvv2.fuzzyValueAt(0);
		    	else
		    		 gperfume = gperfume.fuzzyUnion(fvv2.fuzzyValueAt(0));
	         }
	    }
	    if(workyc.testRuleMatching())
	    { 
	    	fvv=workyc.execute();
	    	if (glight == null)
	    		 glight = fvv.fuzzyValueAt(0);
	    	else
	    		 glight = glight.fuzzyUnion(fvv.fuzzyValueAt(0));
	    	if (gmusic == null)
	    		 gmusic = fvv.fuzzyValueAt(1);
	    	else
	    		 gmusic = gmusic.fuzzyUnion(fvv.fuzzyValueAt(1));
	    	if (gmassage == null)
	    		 gmassage = fvv.fuzzyValueAt(2);
	    	else
	    		 gmassage = gmassage.fuzzyUnion(fvv.fuzzyValueAt(2));
	        if (gctemp == null)
   		         gctemp = fvv.fuzzyValueAt(3);
   	        else
   		         gctemp = gctemp.fuzzyUnion(fvv.fuzzyValueAt(3));
	        
	        perfumeyh.removeAllInputs();
			perfumeym.removeAllInputs(); 
			perfumeyh.addInput(glight);
			perfumeyh.addInput(gmusic);
			perfumeyh.addInput(gmassage);
			perfumeyh.addInput(gctemp);
			perfumeym.addInput(glight);
			perfumeym.addInput(gmusic);
			perfumeym.addInput(gmassage);
			perfumeym.addInput(gctemp);
		    if(perfumeyh.testRuleMatching())
		    {
		        fvv2 = perfumeyh.execute();  
		    	if (gperfume == null)
		    		 gperfume = fvv2.fuzzyValueAt(0);
		    	else
		    		 gperfume = gperfume.fuzzyUnion(fvv2.fuzzyValueAt(0));
		     }
		     if(perfumeym.testRuleMatching())
		     {
		    	fvv2 = perfumeym.execute();  
		    	if (gperfume == null)
		    		 gperfume = fvv2.fuzzyValueAt(0);
		    	else
		    		 gperfume = gperfume.fuzzyUnion(fvv2.fuzzyValueAt(0));
	         }
	    }
	    if(workym.testRuleMatching())
	    { 
	    	fvv=workym.execute();
	    	if (glight == null)
	    		 glight = fvv.fuzzyValueAt(0);
	    	else
	    		 glight = glight.fuzzyUnion(fvv.fuzzyValueAt(0));
	    	if (gmusic == null)
	    		 gmusic = fvv.fuzzyValueAt(1);
	    	else
	    		 gmusic = gmusic.fuzzyUnion(fvv.fuzzyValueAt(1));
	    	if (gmassage == null)
	    		 gmassage = fvv.fuzzyValueAt(2);
	    	else
	    		 gmassage = gmassage.fuzzyUnion(fvv.fuzzyValueAt(2));
	        if (gctemp == null)
   		         gctemp = fvv.fuzzyValueAt(3);
   	        else
   		         gctemp = gctemp.fuzzyUnion(fvv.fuzzyValueAt(3));
	        
	        perfumeyh.removeAllInputs();
			perfumeym.removeAllInputs(); 
			perfumeyh.addInput(glight);
			perfumeyh.addInput(gmusic);
			perfumeyh.addInput(gmassage);
			perfumeyh.addInput(gctemp);
			perfumeym.addInput(glight);
			perfumeym.addInput(gmusic);
			perfumeym.addInput(gmassage);
			perfumeym.addInput(gctemp);
		    if(perfumeyh.testRuleMatching())
		    {
		        fvv2 = perfumeyh.execute();  
		    	if (gperfume == null)
		    		 gperfume = fvv2.fuzzyValueAt(0);
		    	else
		    		 gperfume = gperfume.fuzzyUnion(fvv2.fuzzyValueAt(0));
		     }
		     if(perfumeym.testRuleMatching())
		     {
		    	fvv2 = perfumeym.execute();  
		    	if (gperfume == null)
		    		 gperfume = fvv2.fuzzyValueAt(0);
		    	else
		    		 gperfume = gperfume.fuzzyUnion(fvv2.fuzzyValueAt(0));
	         }
	    }
	    if(workyh.testRuleMatching())
	    { 
	    	fvv=workyh.execute();
	    	if (glight == null)
	    		 glight = fvv.fuzzyValueAt(0);
	    	else
	    		 glight = glight.fuzzyUnion(fvv.fuzzyValueAt(0));
	    	if (gmusic == null)
	    		 gmusic = fvv.fuzzyValueAt(1);
	    	else
	    		 gmusic = gmusic.fuzzyUnion(fvv.fuzzyValueAt(1));
	    	if (gmassage == null)
	    		 gmassage = fvv.fuzzyValueAt(2);
	    	else
	    		 gmassage = gmassage.fuzzyUnion(fvv.fuzzyValueAt(2));
	        if (gctemp == null)
   		         gctemp = fvv.fuzzyValueAt(3);
   	        else
   		         gctemp = gctemp.fuzzyUnion(fvv.fuzzyValueAt(3));
	        
	        perfumeyh.removeAllInputs();
			perfumeym.removeAllInputs(); 
			perfumeyh.addInput(glight);
			perfumeyh.addInput(gmusic);
			perfumeyh.addInput(gmassage);
			perfumeyh.addInput(gctemp);
			perfumeym.addInput(glight);
			perfumeym.addInput(gmusic);
			perfumeym.addInput(gmassage);
			perfumeym.addInput(gctemp);
		    if(perfumeyh.testRuleMatching())
		    {
		        fvv2 = perfumeyh.execute();  
		    	if (gperfume == null)
		    		 gperfume = fvv2.fuzzyValueAt(0);
		    	else
		    		 gperfume = gperfume.fuzzyUnion(fvv2.fuzzyValueAt(0));
		     }
		     if(perfumeym.testRuleMatching())
		     {
		    	fvv2 = perfumeym.execute();  
		    	if (gperfume == null)
		    		 gperfume = fvv2.fuzzyValueAt(0);
		    	else
		    		 gperfume = gperfume.fuzzyUnion(fvv2.fuzzyValueAt(0));
	         }
	    }
	    System.out.println("Lighting in car set to " +
	    		 glight.momentDefuzzify() + " on a scale 1 - 10");
	    System.out.println("Music in car set to " +
	    		 gmusic.momentDefuzzify() + " on a scale 1 - 10");
	    System.out.println("Massaging in car set to " +
	    		 gmassage.momentDefuzzify() + " on a scale 1 - 12");
	    System.out.println("Car Temperature is set to " +
	    		 gctemp.momentDefuzzify() + " C");
	    System.out.println("Car Perfume is set to "+gperfume.momentDefuzzify()+ " on a scale 1-3");
	  }  
}