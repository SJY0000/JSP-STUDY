<% 
	int n = Integer.parseInt(request.getParameter("val"));

	for (int i = 1; i <= 9; i++) {
		out.print(String.format("%d X %d = %d<br> ",n,i,n*i));
	}
	
%> 