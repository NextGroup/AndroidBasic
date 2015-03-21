from flask import Flask, request, send_file;
from flaskext.mysql import MySQL;
import json;
import os;

UPLOAD_FOLDER = "/User/Taejun/Documents/nexta/image"

app = Flask(__name__);
mysql = MySQL();

app.config['MYSQL_DATABASE_USER'] = 'root';
app.config['MYSQL_DATABASE_PASSWORD'] = '1234';
app.config['MYSQL_DATABASE_DB'] = 'nextagram';

mysql.init_app(app);

@app.route("/")
def helloWrold():
	return "helloWrold";

@app.route("/loadData", methods = ["GET", "POST"])
def loadData():

	cursor = mysql.connect().cursor();
	cursor.execute("select * from next_android_nextagram")

	result = []
	columns = tuple( [d[0] for d in cursor.description] )

	for row in cursor:
		result.append(dict(zip(columns, row)))

	print(result);

	return json.dumps(result);

ALLOWED_EXTENSIONS = set(['png', 'jpg', 'jpeg', 'gif'])
def allowedFile(filename):
	return '.' in filename and filename.rsplit('.', 1)[1] in ALLOWED_EXTENSIONS

@app.route("/upload", methods=["POST"])
def upload():
	if request.method == 'POST':
		title = request.form['title']
		writer = request.form['writer']
		id = request.form['id']
		content = request.form['content']
		writeDate = request.form['writeDate']
		imgName = request.form['imgName']

		file = request.files['uploadedfile']
		path = UPLOAD_FOLDER + file.filename
		
		if file and allowedFile(file.filename):
			file.save(path)
			
			con = mysql.connect()
			cursor = con.cursor()
		
			query = "insert into next_android_nextagram \
			(Title, Writer, Id, Content, WriteDate, ImgName) values \
			('" + title + "', '" + writer + "', '" + id + "', '" + content + "', \
			'" + writeDate + "', '" + imgName + "');";
			
			#print(query);
			cursor.execute(query);
			con.commit();

			return "ok"

	return "error"

@app.route("/image/<fileName>", methods=["GET", "POST"])
def loadImage(fileName):
	print("fileName:"+fileName);
	return send_file(UPLOAD_FOLDER+fileName, mimetype='image');
	

if __name__ == "__main__":
	app.run(debug = True, host = '0.0.0.0', port = 5009);
