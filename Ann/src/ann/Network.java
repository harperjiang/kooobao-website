package ann;


public interface Network {

	Unit[] getInputUnits();

	Unit[] getOutputUnits();

	void study(StudyCase studyCase);

	boolean graduated();

	DataSet process(DataSet event);
}
