package pg.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pg.ChequeWriter.ChequeWriter;
import pg.assetModel.AssetInformation;
import pg.assetModel.VendorInformation;
import pg.services.AssetService;
import pg.services.ChequeWriterService;

@Controller
public class ChequeWriterController {
	
	@Autowired
	private ChequeWriterService cwService;

	@RequestMapping(value = "cheque_write",method=RequestMethod.GET)
	public ModelAndView cheque_write(ModelMap map,HttpSession session) {


		String userId=(String)session.getAttribute("userId");
		String userName=(String)session.getAttribute("userName");

		List<ChequeWriter> bankList= cwService.getBankList();
		List<ChequeWriter> chequeWriterList= cwService.getChequeList();
		ModelAndView view = new ModelAndView("cheque_write/cheque_write");

		view.addObject("bankList", bankList);
		view.addObject("chequeWriterList", chequeWriterList);
		
		map.addAttribute("userId",userId);
		map.addAttribute("userName",userName);

		return view; //JSP - /WEB-INF/view/index.jsp
	}
	
	@RequestMapping(value = "/saveChequeWriter",method=RequestMethod.POST)
	public @ResponseBody JSONObject saveChequeWriter(ChequeWriter v)  {

		JSONObject objmain = new JSONObject();
			
			if(cwService.saveChequeWriter(v)) {

				JSONArray mainarray = new JSONArray();
				List<ChequeWriter> List= cwService.getChequeList();
				objmain.put("result", List);
				objmain.put("writerId", List.get(List.size()-1).getWriterId());

			}else {
				objmain.put("result", "Something Wrong");
			}	

		return objmain;
	}
	
	@RequestMapping(value = "/getChequeWriterDetails/{writerId}",method=RequestMethod.GET)
	public @ResponseBody JSONObject getChequeWriterDetails(@PathVariable ("writerId") String writerId)  {

		JSONObject objmain = new JSONObject();
			
		List<ChequeWriter> List= cwService.getChequeWriterDetails(writerId);
		objmain.put("result", List);
		
		return objmain;
	}
	
	@ResponseBody
	@RequestMapping(value = "/printChequeWriteDetailsReport/{writerId}")
	public ModelAndView printAssetDetailsReport(ModelMap map,@PathVariable ("writerId") String writerId) {
		ModelAndView view = new ModelAndView("cheque_write/ChequeWriteDetailsReportPreview");
		map.addAttribute("writerId",writerId);
		return view;
	}
	
	
	@RequestMapping(value = "/addNewBank",method=RequestMethod.POST)
	public @ResponseBody JSONObject addNewBank(ChequeWriter v)  {

		JSONObject objmain = new JSONObject();
			
			if(cwService.addNewBank(v)) {

				List<ChequeWriter> bankList= cwService.getBankList();
				objmain.put("result", bankList);

			}else {
				objmain.put("result", "Something Has Wrong");
			}	

		return objmain;
	}
	
	
	
}
